/**
 * 商品VO（返回值对象）
 */
export interface ProductVO {
  id: number
  name: string
  price: number
  stock: number
  createTime: string
  updateTime: string
}

/**
 * 商品DTO（数据传输对象）
 */
export interface ProductDTO {
  name: string
  price: number
  stock: number
}

/**
 * 分页响应
 */
export interface PageResponse<T> {
  records: T[]
  total: number
  pages: number
  current: number
  size: number
}
