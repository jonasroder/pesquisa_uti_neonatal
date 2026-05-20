import {createVuetify} from 'vuetify'
import * as components from 'vuetify/components'
import * as directives from 'vuetify/directives'
import {aliases, fa} from 'vuetify/iconsets/fa'
import '../styles/main.scss';

export default createVuetify({
	theme: {
		defaultTheme: 'light',
		themes      : {
			light: {
				colors: {
					primary   : '#0882a0',
					secondary : '#64748B',
					accent    : '#0e6a85',
					background: '#EEF2F7',

					azulEscuro   : '#0f2a45',
					cinzaAzulado : '#94A3B8',

					textColor: '#334155',
					iconColor: '#64748B',
				}
			},
			dark : {
				dark  : true,
				colors: {
					primary   : '#388E3C',
					secondary : '#66BB6A',
					accent    : '#81C784',
					background: '#2E7D32',
				}
			},
		},
	},

	icons: {
		defaultSet: 'fa',
		aliases,
		sets      : {
			fa,
		},
	},

	defaults: {
		global       : {
			ripple   : true,
			textColor: 'textColor',
			iconColor: 'iconColor',
		},
		VAutocomplete: {
			multiple     : false,
			clearable    : true,
			chips        : false,
			closableChips: true,
			itemTitle    : 'label',
			itemValue    : 'value',
			variant      : 'outlined',
			color        : 'primary',
			density      : 'comfortable',
		},
		VTextField   : {
			variant: 'outlined',
			color  : 'primary',
			density: 'comfortable',
		},
		VTextarea    : {
			variant: 'outlined',
			color  : 'primary',
			density: 'comfortable',
		},
		VRadio       : {
			color: 'primary',
		},
		VRadioGroup  : {
			color : 'primary',
			inline: true,
		},
		VSwitch      : {
			color: 'primary',
			inset: true,
		},
		VFileInput   : {
			variant: 'outlined',
			color  : 'primary',
			density: 'comfortable',
		},
		VTooltip     : {
			activator: 'parent',
			location : 'top'
		},
		VBtn         : {
			ripple: 'true',
		},
	},

	components,
	directives,
})
