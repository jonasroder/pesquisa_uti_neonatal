const {defineConfig} = require('@vue/cli-service');
const path = require('path');

module.exports = defineConfig({
	outputDir: path.resolve(__dirname, '../../src/main/resources/static'),
	transpileDependencies: true,
	configureWebpack: {
		resolve: {
			alias: {
				'components': '@/components',
				'service': '@/service',
				'assets': '@/assets',
			}
		},
		plugins: [
			// Aqui, você pode adicionar qualquer plugin do Webpack que você possa precisar
			// Exemplo: new MyWebpackPlugin()
		]
	},
	devServer: {
		port: 3000,
		proxy: {
			'/api': {
				target: 'http://localhost:8080',
				changeOrigin: true,
				ws: true,
			},
			'/authenticate': {
				target: 'http://localhost:8080',
				changeOrigin: true,
				ws: true,
			},
		}
	}
});

