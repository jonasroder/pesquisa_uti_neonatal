import axios from 'axios';
import { authStore, setAccessToken, clearAccessToken } from './authStore';
import { clearSession, decodeTokenAndStoreSession } from './tokenService';
import router from '@/router';
import { setNotification } from '@/plugins/notificationService';

let isRefreshing = false;
let refreshQueue = [];

const processQueue = (error, token = null) => {
    refreshQueue.forEach(({ resolve, reject }) => {
        error ? reject(error) : resolve(token);
    });
    refreshQueue = [];
};

const doLogout = () => {
    clearAccessToken();
    clearSession();
    router.push('/login');
};


axios.interceptors.request.use(
    config => {
        const isAuthRoute = config.url?.endsWith('/login') || config.url?.endsWith('/renew') || config.url?.endsWith('/logout');
        if (isAuthRoute) return config;

        if (authStore.accessToken) {
            config.headers.Authorization = `Bearer ${authStore.accessToken}`;
        }
        return config;
    },
    error => Promise.reject(error)
);


axios.interceptors.response.use(
    response => response,
    async error => {
        const originalRequest = error.config;

        if (!error.response) return Promise.reject(error);

        const isAuthRoute = originalRequest.url?.endsWith('/login')
            || originalRequest.url?.endsWith('/renew')
            || originalRequest.url?.endsWith('/logout');

        if (error.response.status === 401 && !originalRequest._retry && !isAuthRoute) {
            if (isRefreshing) {
                return new Promise((resolve, reject) => {
                    refreshQueue.push({ resolve, reject });
                }).then(token => {
                    originalRequest.headers.Authorization = `Bearer ${token}`;
                    return axios(originalRequest);
                });
            }

            originalRequest._retry = true;
            isRefreshing = true;

            try {
                const res = await axios.post('/authenticate/renew');
                const newToken = res.data.token;
                setAccessToken(newToken);
                decodeTokenAndStoreSession(newToken);
                processQueue(null, newToken);
                originalRequest.headers.Authorization = `Bearer ${newToken}`;
                return axios(originalRequest);
            } catch (refreshError) {
                processQueue(refreshError);
                if (refreshError.response) {
                    doLogout();
                }
                return Promise.reject(refreshError);
            } finally {
                isRefreshing = false;
            }
        }

        if (error.response.status === 403) {
            setNotification('Acesso negado.', 'warning');
        }

        return Promise.reject(error);
    }
);

export default axios;
