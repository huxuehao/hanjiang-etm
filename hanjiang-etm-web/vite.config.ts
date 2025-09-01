import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import vueJsx from '@vitejs/plugin-vue-jsx'
import vueSetupExtend from 'vite-plugin-vue-setup-extend'
import vueDevTools from 'vite-plugin-vue-devtools'
import zipPack, { Options as ZipPickOptions } from "vite-plugin-zip-pack"

export default defineConfig(({ mode, command }) => {
  // 压缩dist配置
  const zipPickOptions: ZipPickOptions = {
    inDir: 'dist',
    outFileName: 'dist.zip',
  }

  return {
    // 插件
    plugins: [
      vue(),
      vueJsx(),
      vueSetupExtend(),
      vueDevTools(),
      zipPack(zipPickOptions),
    ],
    resolve: {
      // 别名
      alias: {
        '@': fileURLToPath(new URL('./src', import.meta.url))
      },
    },
    // 开发服务器配置
    server: {
      // 指定服务器端口
      port: 5173,
      // 指定开发服务器绑定的主机地址，绑定到0.0.0.0，支持外部设备通过你的机器的 IP 地址访问本地服务器
      host: true,
      // 启动服务器后是否自动打开浏览器
      open: false,
      // 代理配置，避免跨域
      proxy: {
        '/api': {
          target: 'http://127.0.0.1:8190',
          changeOrigin: true,
          rewrite: (p) => {
            return p.replace(/^\/api/, '')
          }
        }
      }
    },
    // 重要：设置公共路径，确保资源从/web/路径加载
    base: `/web/`,
    // 构建配置
    build: {
      outDir: 'dist',
      assetsDir: '', // 清空assetsDir，让资源直接输出到dist根目录
      rollupOptions: {
        output: {
          chunkFileNames: 'js/[name]-[hash].js',
          entryFileNames: 'js/[name]-[hash].js',
          assetFileNames: '[ext]/[name]-[hash].[ext]'
        }
      }
    },
    css: {
      preprocessorOptions: {
        scss: {
          javascriptEnabled: true,
        },
      }
    }
  }
})
