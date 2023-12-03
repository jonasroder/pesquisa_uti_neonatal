import axios from "axios";
import { getToken, removeToken } from "@/service/tokenService";
import router from "@/router"; // Asumindo que você tem um arquivo router.js que exporta uma instância do VueRouter

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
		if (error.response && error.response.status === 401) {
			// Se a resposta do erro for 401, limpa o token e redirecione para o login
			removeToken(); // Limpa o token armazenado
			router.push('/login'); // Redireciona para a página de login
		}
		return Promise.reject(error);
	}
);

export default axios;
