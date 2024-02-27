const TOKEN_KEY = 'userToken';

export const saveToken = (token) => {
	const expiresAt = new Date().getTime() + (480 * 60 * 1000); // 8 horas em milissegundos
	localStorage.setItem(TOKEN_KEY, token);
	localStorage.setItem('tokenExpiresAt', expiresAt.toString());
};


export const isTokenExpired = () => {
	const expiresAt = localStorage.getItem('tokenExpiresAt');
	const now = new Date().getTime();
	const isExpired = now >= parseInt(expiresAt, 10);
	return isExpired;
};


export const getToken = () => {
	return localStorage.getItem(TOKEN_KEY);
};


export const removeToken = () => {
	localStorage.removeItem(TOKEN_KEY);
	localStorage.removeItem('cachedRoutes');
};

