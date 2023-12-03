import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import './plugins/axios'
import vuetify from './plugins/vuetify'
import Toastify from 'vue3-toastify';
import './service/axiosConfig';

import 'vue3-toastify/dist/index.css';
import 'vuetify/styles'
import './styles/main.scss';
import '@fortawesome/fontawesome-free/css/all.css'

createApp(App)
	.use(router)
	.use(vuetify)
	.use(Toastify)
	.mount('#app')
