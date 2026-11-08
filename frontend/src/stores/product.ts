import { defineStore } from 'pinia'
import { ref, computed } from 'vue'
import * as productApi from '@/api/product'
import type { ProductVO, PageResponse } from '@/types'

export const useProductStore = defineStore('product', () => {
  const productList = ref<ProductVO[]>([])
  const total = ref(0)
  const current = ref(1)
  const pageSize = ref(10)
  const searchName = ref('')
  const loading = ref(false)

  /**
   * 计算分页信息
   */
  const pages = computed(() => Math.ceil(total.value / pageSize.value))

  /**
   * 获取商品列表
   */
  const fetchProductList = async () => {
    loading.value = true
    try {
      const response: PageResponse<ProductVO> = await productApi.getProductList({
        current: current.value,
        pageSize: pageSize.value,
        name: searchName.value || undefined
      })
      productList.value = response.records || []
      total.value = response.total
      current.value = response.current
      pageSize.value = response.size
    } catch (error) {
      console.error('获取商品列表失败:', error)
      throw error
    } finally {
      loading.value = false
    }
  }

  /**
   * 删除商品
   */
  const deleteProduct = async (id: number) => {
    try {
      await productApi.deleteProduct(id)
      await fetchProductList()
    } catch (error) {
      console.error('删除商品失败:', error)
      throw error
    }
  }

  /**
   * 设置搜索词
   */
  const setSearchName = (name: string) => {
    searchName.value = name
    current.value = 1
  }

  /**
   * 重置搜索
   */
  const resetSearch = () => {
    searchName.value = ''
    current.value = 1
  }

  /**
   * 设置当前页
   */
  const setCurrentPage = (page: number) => {
    current.value = page
  }

  /**
   * 设置每页数量
   */
  const setPageSize = (size: number) => {
    pageSize.value = size
    current.value = 1
  }

  return {
    productList,
    total,
    current,
    pageSize,
    searchName,
    loading,
    pages,
    fetchProductList,
    deleteProduct,
    setSearchName,
    resetSearch,
    setCurrentPage,
    setPageSize
  }
})
