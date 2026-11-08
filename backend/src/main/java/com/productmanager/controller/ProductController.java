package com.productmanager.controller;

import com.productmanager.common.ApiResponse;
import com.productmanager.common.PageResponse;
import com.productmanager.dto.ProductDTO;
import com.productmanager.service.ProductService;
import com.productmanager.vo.ProductVO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 商品管理控制器
 */
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    /**
     * 分页查询商品列表
     *
     * @param current  当前页码（默认1）
     * @param pageSize 每页数量（默认10）
     * @param name     商品名称（可选，支持模糊查询）
     * @return 商品分页数据
     */
    @GetMapping
    public ApiResponse<PageResponse<ProductVO>> getProductList(
            @RequestParam(defaultValue = "1") Integer current,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String name) {
        PageResponse<ProductVO> page = productService.getProductList(current, pageSize, name);
        return ApiResponse.success(page);
    }

    /**
     * 获取商品详情
     *
     * @param id 商品ID
     * @return 商品详情
     */
    @GetMapping("/{id}")
    public ApiResponse<ProductVO> getProductById(@PathVariable Long id) {
        ProductVO productVO = productService.getProductById(id);
        return ApiResponse.success(productVO);
    }

    /**
     * 新增商品
     *
     * @param productDTO 商品数据
     * @return 新增的商品
     */
    @PostMapping
    public ApiResponse<ProductVO> createProduct(@Valid @RequestBody ProductDTO productDTO) {
        ProductVO productVO = productService.createProduct(productDTO);
        return ApiResponse.success(productVO);
    }

    /**
     * 修改商品
     *
     * @param id         商品ID
     * @param productDTO 商品数据
     * @return 修改后的商品
     */
    @PutMapping("/{id}")
    public ApiResponse<ProductVO> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductDTO productDTO) {
        ProductVO productVO = productService.updateProduct(id, productDTO);
        return ApiResponse.success(productVO);
    }

    /**
     * 删除商品
     *
     * @param id 商品ID
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ApiResponse.success();
    }
}
