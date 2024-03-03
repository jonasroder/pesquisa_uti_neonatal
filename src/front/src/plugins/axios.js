import axios from "axios";
import {removeToken, getToken, saveToken, isTokenExpired} from "@/service/common/tokenService";
import router from '@/router';
import {setNotification} from "@/plugins/notificationService";

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

	if (!getToken() || isTokenExpired()) {
		router.push('/login');
		return Promise.reject("No token found");
	}

	if (!config.url.endsWith('/renew') && !config.url.endsWith('/autocomplete')) {
		renovarToken()
	}

	return config;
}, error => {
	return Promise.reject(error);
});


const renovarToken = async () => {
	try {
		const res = await axios.post('/authenticate/renew');
		saveToken(res.data.token);
		return res.data;

	} catch (e) {
		console.error(e);
		setNotification("Ocorreu um erro durante a autenticação." + e, "error");
	}
}

export default axios;
