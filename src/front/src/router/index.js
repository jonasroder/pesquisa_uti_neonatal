import { createRouter, createWebHistory } from 'vue-router';
import axios from 'axios';
import { authStore, setAccessToken } from '@/service/common/authStore';
import { clearSession, decodeTokenAndStoreSession } from '@/service/common/tokenService';
import { getMenu, getRoutes } from '@/service/menu';

const viewModules = import.meta.glob('/src/views/**/*.vue');


export const loadRoutes = async () => {
    try {
        const [dynamicRoutes, dynamicMenu] = await Promise.all([getRoutes(), getMenu()]);
        localStorage.setItem('cachedRoutes', JSON.stringify(dynamicRoutes));
        localStorage.setItem('cachedMenu', JSON.stringify(dynamicMenu));
        addDynamicRoutes();
        return dynamicRoutes;
    } catch (error) {
        console.error('Erro ao carregar rotas:', error);
        return [];
    }
};


const addDynamicRoutes = () => {
    const cachedRoutes = localStorage.getItem('cachedRoutes');
    if (!cachedRoutes) return;

    const routesObj = JSON.parse(cachedRoutes);
    routesObj.forEach(route => {
        const key = `/src/views/${route.component}`;
        router.addRoute({
            path     : route.path,
            name     : route.name,
            component: viewModules[key],
            meta     : { title: route.title },
        });
    });
};


const initializeAuth = async () => {
    try {
        const res = await axios.post('/authenticate/renew');
        setAccessToken(res.data.token);
        decodeTokenAndStoreSession(res.data.token);
        addDynamicRoutes();
    } catch {
        clearSession();
    } finally {
        authStore.initialized = true;
    }
};


const staticRoutes = [
    { path: '/', redirect: '/login' },
    {
        path     : '/login',
        name     : 'Login-Page',
        component: () => import('@/views/login/index.vue'),
        meta     : { title: 'Saúde em Equilibrio - Login' },
    },
    { path: '/:pathMatch(.*)*', redirect: '/login' },
];


const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes : staticRoutes,
});


router.beforeEach(async (to, from, next) => {
    if (!authStore.initialized) {
        await initializeAuth();
    }

    if (to.name === 'Login-Page') {
        authStore.accessToken ? next({ name: 'Dashboard-Home' }) : next();
    } else if (!authStore.accessToken) {
        next({ name: 'Login-Page' });
    } else {
        next();
    }
});


router.afterEach((to) => {
    document.title = to.meta.title || 'Saúde em Equilíbrio';
});


export default router;
