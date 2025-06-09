import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import './plugins/axios'
import vuetify from './plugins/vuetify'
import Toastify from 'vue3-toastify';
import './service/common/axiosConfig';
import { vMaska } from "maska";
import 'vue3-toastify/dist/index.css';
import 'vuetify/styles'
import './styles/main.scss';
import '@fortawesome/fontawesome-free/css/all.css'
import VueApexCharts from 'vue3-apexcharts';


createApp(App)
	.use(router)
	.use(vuetify)
	.use(Toastify)
	.use(VueApexCharts)
	.directive("maska", vMaska)
	.component('ApexChart', VueApexCharts)
	.mount('#app');

