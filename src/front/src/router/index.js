import { createRouter, createWebHistory } from 'vue-router'
import { getToken } from '@/service/common/tokenService';

const routes = [
	{
		path: '/',
		name: 'Home',
		component: () => import("@/views/main-page/main-page.vue"),
		meta: { title: 'Jonas e Denise' }
	},
	{
		path: '/login',
		name: 'Login-Page',
		component: () => import("@/views/login/index.vue"),
		meta: { title: 'Saúde em Equilibrio - Login' }
	},
	{
		path: '/paciente/edit',
		name: 'Paciente-Page',
		component: () => import("@/views/paciente-page/edit.vue"),
		meta: { title: 'Cadastrar Paciente' }
	},
	{
		path: '/paciente/index',
		name: 'Paciente-List',
		component: () => import("@/views/paciente-page/index.vue"),
		meta: { title: 'Lista Pacientes' }
	},
	{
		path: '/consulta/edit',
		name: 'Consulta-Page',
		component: () => import("@/views/consulta-page/edit.vue"),
		meta: { title: 'Consulta' }
	},

];

const router = createRouter({
	history: createWebHistory(process.env.BASE_URL),
	routes,
})

router.beforeEach((to, from, next) => {
	if (to.name !== 'Login-Page' && !getToken()) {
		next({ name: 'Login-Page' });
	} else {
		next();
	}
});

router.afterEach((to) => {
	document.title = to.meta.title || 'Saúde em Equilíbrio';
});

export default router;
