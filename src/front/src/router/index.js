import { createRouter, createWebHistory } from 'vue-router'
import Login from '../views/login/login.vue'
import MainPage from '../views/main-page/main-page.vue'

const routes = [
	{
		path: '/',
		name: 'MainPage',
		component: MainPage
	},
	{
		path: '/login',
		name: 'Login',
		component: Login
	},
	// adicione mais rotas conforme necessário
]

const router = createRouter({
	history: createWebHistory(process.env.BASE_URL),
	routes
})

export default router
