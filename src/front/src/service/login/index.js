import axios from 'axios';
import { setNotification } from '@/plugins/notificationService';
import { setAccessToken, clearAccessToken } from '@/service/common/authStore';
import { clearSession, decodeTokenAndStoreSession } from '@/service/common/tokenService';
import { loadRoutes } from '@/router';

export const serviceAuthenticateUser = async (data) => {
    try {
        clearSession();
        const res = await axios.post('/authenticate/login', data);
        setAccessToken(res.data.token);
        decodeTokenAndStoreSession(res.data.token);
        await loadRoutes();
        return res.data;
    } catch (e) {
        console.error(e);
        setNotification('Usuário ou senha inválidos.', 'error');
    }
};

export const serviceLogout = async () => {
    try {
        await axios.post('/authenticate/logout');
    } catch (e) {
        console.error(e);
    } finally {
        clearAccessToken();
        clearSession();
    }
};
