import { createRouter, createWebHistory } from 'vue-router'

const routes = [
	{
		path: '/',
		name: 'MainPage',
		component: () => import("../views/main-page/main-page.vue"),
	},
	{
		path: '/login',
		name: 'Login-Page',
		component: () => import("../views/login/Login-Page.vue"),
	},
	// adicione mais rotas conforme necessário
]

const router = createRouter({
	history: createWebHistory(process.env.BASE_URL),
	routes
})

export default router
