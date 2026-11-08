import request from './request'
import type { ProductVO, ProductDTO, PageResponse } from '@/types'

/**
 * 获取商品列表（分页）
 */
export const getProductList = (params: {
  current: number
  pageSize: number
  name?: string
}): Promise<PageResponse<ProductVO>> => {
  return request.get('/products', { params })
}

/**
 * 获取商品详情
 */
export const getProductById = (id: number): Promise<ProductVO> => {
  return request.get(`/products/${id}`)
}

/**
 * 创建商品
 */
export const createProduct = (data: ProductDTO): Promise<ProductVO> => {
  return request.post('/products', data)
}

/**
 * 修改商品
 */
export const updateProduct = (id: number, data: ProductDTO): Promise<ProductVO> => {
  return request.put(`/products/${id}`, data)
}

/**
 * 删除商品
 */
export const deleteProduct = (id: number): Promise<void> => {
  return request.delete(`/products/${id}`)
}
