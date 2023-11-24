import { createApp } from 'vue'
import App from './App.vue'
import router from './router'

import 'bootstrap/dist/css/bootstrap.css' // Importe os estilos CSS do Bootstrap
import 'bootstrap/dist/js/bootstrap.bundle.min.js' // Importe o JavaScript do Bootstrap (se necessário)
import '@fortawesome/fontawesome-free/css/all.css'


createApp(App).use(router).mount('#app')
