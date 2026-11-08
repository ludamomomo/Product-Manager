import axios, { AxiosInstance, AxiosResponse } from 'axios'

/**
 * API 响应格式
 */
interface ApiResponse<T> {
  code: number
  message: string
  data: T | null
}

/**
 * 创建 axios 实例
 */
const instance: AxiosInstance = axios.create({
  baseURL: '/api',
  timeout: 10000,
  headers: {
    'Content-Type': 'application/json'
  }
})

/**
 * 响应拦截器
 */
instance.interceptors.response.use(
  (response: AxiosResponse<ApiResponse<any>>) => {
    const { code, data, message } = response.data

    if (code === 200) {
      return data
    }

    throw new Error(message || '请求失败')
  },
  (error) => {
    console.error('请求错误:', error)
    throw error
  }
)

export default instance
