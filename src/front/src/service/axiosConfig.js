import axios from "axios";
import { getToken, removeToken } from "@/service/tokenService";
import router from "@/router";

axios.interceptors.request.use(
	config => {
		const token = getToken();
		if (token) {
			config.headers.Authorization = `Bearer ${token}`;
		}
		return config;
	},
	error => {
		return Promise.reject(error);
	}
);

axios.interceptors.response.use(
	response => response,
	error => {
		if (error.response.status === 401 || error.response.status === 403) {
			// Se a resposta do erro for 401 ou 403, limpa o token e redirecione para o login
			removeToken(); // Limpa o token armazenado
			router.push('/login'); // Redireciona para a página de login
		}
		return Promise.reject(error);
	}
);

export default axios;
