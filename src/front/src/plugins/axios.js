import axios from "axios";
import { removeToken } from "@/service/tokenService";
import router from '@/router'

axios.interceptors.response.use(response => response, error => {
	if (error.response && error.response.status === 401) {
		removeToken();
		router.push('/login');
	}
	return Promise.reject(error);
});

export default axios;
