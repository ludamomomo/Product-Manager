import { createRouter, createWebHistory } from 'vue-router'
import type { RouteRecordRaw } from 'vue-router'
import ProductPage from '@/views/ProductPage.vue'

const routes: RouteRecordRaw[] = [
  {
    path: '/',
    name: 'home',
    component: ProductPage
  },
  {
    path: '/products',
    name: 'products',
    component: ProductPage
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
