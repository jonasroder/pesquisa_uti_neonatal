import { reactive } from 'vue';

export const authStore = reactive({
    accessToken: null,
    initialized: false,
});

export const setAccessToken = (token) => {
    authStore.accessToken = token;
};

export const clearAccessToken = () => {
    authStore.accessToken = null;
};
