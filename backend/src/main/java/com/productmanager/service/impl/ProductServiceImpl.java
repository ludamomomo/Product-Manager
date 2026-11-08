package com.productmanager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.productmanager.common.PageResponse;
import com.productmanager.dto.ProductDTO;
import com.productmanager.entity.Product;
import com.productmanager.mapper.ProductMapper;
import com.productmanager.service.ProductService;
import com.productmanager.vo.ProductVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 商品服务实现类
 */
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;

    @Override
    public PageResponse<ProductVO> getProductList(int current, int pageSize, String name) {
        // 创建分页对象
        Page<Product> page = new Page<>(current, pageSize);

        // 构建查询条件
        LambdaQueryWrapper<Product> wrapper = new LambdaQueryWrapper<>();
        if (name != null && !name.trim().isEmpty()) {
            wrapper.like(Product::getName, name);
        }

        // 按创建时间倒序排列
        wrapper.orderByDesc(Product::getCreateTime);

        // 查询
        Page<Product> result = productMapper.selectPage(page, wrapper);

        // 转换为VO对象并构建响应
        PageResponse<ProductVO> response = PageResponse.<ProductVO>builder()
                .current((long) result.getCurrent())
                .size((long) result.getSize())
                .total(result.getTotal())
                .pages((long) result.getPages())
                .records(new java.util.ArrayList<>())
                .build();

        for (Product product : result.getRecords()) {
            response.getRecords().add(convertToVO(product));
        }

        return response;
    }

    @Override
    public ProductVO getProductById(Long id) {
        Product product = productMapper.selectById(id);
        if (product == null) {
            throw new RuntimeException("商品不存在");
        }
        return convertToVO(product);
    }

    @Override
    public ProductVO createProduct(ProductDTO productDTO) {
        Product product = Product.builder()
                .name(productDTO.getName())
                .price(productDTO.getPrice())
                .stock(productDTO.getStock())
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();

        productMapper.insert(product);
        return convertToVO(product);
    }

    @Override
    public ProductVO updateProduct(Long id, ProductDTO productDTO) {
        Product product = productMapper.selectById(id);
        if (product == null) {
            throw new RuntimeException("商品不存在");
        }

        if (productDTO.getName() != null) {
            product.setName(productDTO.getName());
        }
        if (productDTO.getPrice() != null) {
            product.setPrice(productDTO.getPrice());
        }
        if (productDTO.getStock() != null) {
            product.setStock(productDTO.getStock());
        }
        product.setUpdateTime(LocalDateTime.now());

        productMapper.updateById(product);
        return convertToVO(product);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productMapper.selectById(id);
        if (product == null) {
            throw new RuntimeException("商品不存在");
        }
        productMapper.deleteById(id);
    }

    /**
     * 将Product实体转换为ProductVO
     */
    private ProductVO convertToVO(Product product) {
        return ProductVO.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .stock(product.getStock())
                .createTime(product.getCreateTime())
                .updateTime(product.getUpdateTime())
                .build();
    }
}
