import axios from "axios";
import { removeToken, getToken } from "@/service/common/tokenService";
import router from '@/router';

axios.interceptors.response.use(response => response, error => {
	if (error.response && error.response.status === 401) {
		removeToken();
		router.push('/login');
	}
	return Promise.reject(error);
});


axios.interceptors.request.use(config => {
	if (config.url.endsWith('/login')) {
		return config;
	}
	if (!getToken()) {
		router.push('/login');
		return Promise.reject("No token found");
	}
	return config;
}, error => {
	return Promise.reject(error);
});


export default axios;
