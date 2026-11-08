package com.productmanager.service;

import com.productmanager.common.PageResponse;
import com.productmanager.dto.ProductDTO;
import com.productmanager.vo.ProductVO;

/**
 * 商品服务接口
 */
public interface ProductService {

    /**
     * 分页查询商品列表
     *
     * @param current  当前页码
     * @param pageSize 每页数量
     * @param name     商品名称（模糊查询，可选）
     * @return 分页结果
     */
    PageResponse<ProductVO> getProductList(int current, int pageSize, String name);

    /**
     * 获取商品详情
     *
     * @param id 商品ID
     * @return 商品详情
     */
    ProductVO getProductById(Long id);

    /**
     * 新增商品
     *
     * @param productDTO 商品DTO
     * @return 新增的商品
     */
    ProductVO createProduct(ProductDTO productDTO);

    /**
     * 修改商品
     *
     * @param id         商品ID
     * @param productDTO 商品DTO
     * @return 修改后的商品
     */
    ProductVO updateProduct(Long id, ProductDTO productDTO);

    /**
     * 删除商品
     *
     * @param id 商品ID
     */
    void deleteProduct(Long id);
}
