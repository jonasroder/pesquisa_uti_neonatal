import { createVuetify } from 'vuetify'
import * as components from 'vuetify/components'
import * as directives from 'vuetify/directives'
import { aliases, fa } from 'vuetify/iconsets/fa'

export default createVuetify({
	theme: {
		defaultTheme: 'light', // Mova defaultTheme para fora do objeto themes
		themes: {
			light: {
				colors: {
					barrasSperior: '#00bdda',
					barraLateral :'#f6f8f8',
					formArea: '#ffffff',
					azulEscuro: '#34568B',
					cinzaAzulado: '#9EADBA',
					primary: '#6fbfd9',
					secondary: '#b0bec5',
					accent: '#8c9eff',
					background: '#d7e3e5',
				}
			},
			dark: {
				dark: true,
				colors: {
					primary: '#388E3C',
					secondary: '#66BB6A',
					accent: '#81C784',
					background: '#2E7D32',
				}
			},
		},
	},

	icons: {
		defaultSet: 'fa',
		aliases,
		sets: {
			fa,
		},
	},

	defaults: {
		global: {
			ripple: true // Corrija o erro de digitação aqui
		}
	},

	components,
	directives,
})
