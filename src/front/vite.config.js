import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import { fileURLToPath, URL } from 'node:url'

export default defineConfig({
	plugins: [vue()],
	define: {
		global: 'globalThis',
	},
	resolve: {
		alias: {
			'@': fileURLToPath(new URL('./src', import.meta.url)),
			'components': fileURLToPath(new URL('./src/components', import.meta.url)),
			'service': fileURLToPath(new URL('./src/service', import.meta.url)),
			'assets': fileURLToPath(new URL('./src/assets', import.meta.url)),
		}
	},
	build: {
		outDir: fileURLToPath(new URL('../../src/main/resources/static', import.meta.url)),
		emptyOutDir: true,
	},
	server: {
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
			'/uploads': {
				target: 'http://localhost:8080',
				changeOrigin: true,
			},
			'/ws': {
				target: 'http://localhost:8080',
				ws: true,
				changeOrigin: true,
			}
		}
	}
})
