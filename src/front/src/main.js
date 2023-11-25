import { createApp } from 'vue'
import App from './App.vue'
import router from './router'

import 'bootstrap/dist/js/bootstrap.bundle.min.js'
import './styles/main.scss';
import '@fortawesome/fontawesome-free/css/all.css'



createApp(App).use(router).mount('#app')
