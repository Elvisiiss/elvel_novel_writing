import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import vueDevTools from 'vite-plugin-vue-devtools'

export default defineConfig({
  plugins: [
    vue(),
    vueDevTools(),
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    },
  },
  server: {
    host: '0.0.0.0', // 监听所有 IPv4 地址
    port: 31150,      // 指定端口（默认 5173）
    strictPort: true, // 如果端口被占用，直接退出
    allowedHosts: [
      'cal' // 允许 cal 主机名访问
    ],
    proxy: {
      // 匹配所有以 /api 开头的请求路径
      '/api': {
        target: 'http://127.168.82.63:60000', // 后端API地址
        changeOrigin: true, // 允许跨域
        rewrite: (path) => path.replace(/^\/api/, '') // 移除请求路径中的 /api 前缀
      }
    }
  }
})


// netstat -ano | findstr "5173"
// TCP    0.0.0.0:5173           0.0.0.0:0              LISTENING       12345
// taskkill /PID 12345 /F
