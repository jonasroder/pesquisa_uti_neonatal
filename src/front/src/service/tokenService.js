import axios from "axios";

const TOKEN_KEY = 'userToken';

export const saveToken = (token) => {
	localStorage.setItem(TOKEN_KEY, token);
};

export const getToken = () => {
	return localStorage.getItem(TOKEN_KEY);
};

export const removeToken = () => {
	localStorage.removeItem(TOKEN_KEY);
};

export const setAuthHeader = () => {
	axios.defaults.headers.common['Authorization'] = `Bearer ${getToken()}`;
};

export const clearAuthHeader = () => {
	axios.defaults.headers.common['Authorization'] = null;
};
