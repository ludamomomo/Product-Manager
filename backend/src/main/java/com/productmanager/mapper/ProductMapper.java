package com.productmanager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.productmanager.entity.Product;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品 Mapper
 */
@Mapper
public interface ProductMapper extends BaseMapper<Product> {
}
