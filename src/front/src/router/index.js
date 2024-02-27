import {createRouter, createWebHistory} from 'vue-router'
import {getToken} from '@/service/common/tokenService';
import {getRoutes} from "@/service/menu";

export const loadRoutes = async () => {
	let cachedRoutes = localStorage.getItem('cachedRoutes');
	let routesObj    = cachedRoutes ? JSON.parse(cachedRoutes) : null;

	if (!cachedRoutes || (routesObj && routesObj.error)) {
		try {
			let dynamicRoutes = await getRoutes();
			localStorage.setItem('cachedRoutes', JSON.stringify(dynamicRoutes));
			await addDynamicRoutes();
			return dynamicRoutes;
		} catch (error) {
			console.error("Erro ao carregar rotas:", error);
			return [];
		}
	} else {
		return routesObj;
	}
};


const addDynamicRoutes = () => {
	let cachedRoutes = localStorage.getItem('cachedRoutes');
	let routesObj    = cachedRoutes ? JSON.parse(cachedRoutes) : null;


	if (routesObj != null) {
		routesObj.forEach(route => {

			const rota = {
				path     : route.path,
				name     : route.name,
				component: () => import(`@/views/${route.component}`),
				meta     : {title: route.title}
			}

			router.addRoute(rota);

		});
	}
};


const staticRoutes = [{
	path     : '/login',
	name     : 'Login-Page',
	component: () => import("@/views/login/index.vue"),
	meta     : {title: 'Saúde em Equilibrio - Login'}
}];


const router = createRouter({
	history: createWebHistory(process.env.BASE_URL),
	routes : staticRoutes,
})

router.beforeEach((to, from, next) => {
	if (to.name !== 'Login-Page' && !getToken()) {
		next({name: 'Login-Page'});
	} else {
		next();
	}
});

router.afterEach((to) => {
	document.title = to.meta.title || 'Saúde em Equilíbrio';
});

addDynamicRoutes();
export default router;
