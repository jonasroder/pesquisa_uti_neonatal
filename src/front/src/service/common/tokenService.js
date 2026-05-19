export const getSessionUserData = () => {
    return JSON.parse(localStorage.getItem('userData'));
};

export const clearSession = () => {
    localStorage.removeItem('userData');
    localStorage.removeItem('cachedRoutes');
    localStorage.removeItem('cachedMenu');
};

export const decodeTokenAndStoreSession = (token) => {
    const base64Url = token.split('.')[1];
    const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    const jsonPayload = decodeURIComponent(window.atob(base64).split('').map(c => {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));
    const payload = JSON.parse(jsonPayload);
    const dataUser = JSON.parse(payload.sub);
    localStorage.setItem('userData', JSON.stringify(dataUser));
    return dataUser;
};
