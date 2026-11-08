package com.productmanager.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品数据传输对象 - 用于新增和修改请求
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 商品名称 - 必填
     */
    @NotBlank(message = "商品名称不能为空")
    private String name;

    /**
     * 商品价格 - 必填，不能小于0
     */
    @NotNull(message = "商品价格不能为空")
    @DecimalMin(value = "0", inclusive = false, message = "商品价格必须大于0")
    private BigDecimal price;

    /**
     * 商品库存 - 必填，不能小于0
     */
    @NotNull(message = "商品库存不能为空")
    @Min(value = 0, message = "商品库存不能小于0")
    private Integer stock;
}
