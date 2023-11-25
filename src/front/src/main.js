import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import Toastify from 'vue3-toastify';
import 'vue3-toastify/dist/index.css';
import 'vuetify/styles'
import './styles/main.scss';
import 'bootstrap/dist/js/bootstrap.bundle.min.js'
import '@fortawesome/fontawesome-free/css/all.css'

import { createVuetify } from 'vuetify'
import * as components from 'vuetify/components'
import * as directives from 'vuetify/directives'
import { aliases, fa } from 'vuetify/iconsets/fa';


const vuetify = createVuetify({
	icons: {
		defaultSet: 'fa',
		aliases,
		sets: {
			fa,
		},
	},
	components,
	directives,
})


createApp(App).use(router).use(vuetify).use(Toastify).mount('#app')
