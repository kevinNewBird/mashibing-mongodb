package com.mashibing.mongodb.entity;

import lombok.Data;

/**
 * description  OrderDetail <BR>
 * <p>
 * author: zhao.song
 * date: created in 14:38  2021/8/4
 * company: TRS信息技术有限公司
 * version 1.0
 */
@Data
public class OrderDetail {

    /**
     * ID
     */
    private Long id;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 商品编码
     */
    private String sku;
    /**
     * 购买数量
     */
    private Integer qty ;
    /**
     * 单价
     */
    private Integer price ;
    /**
     * 进货价格
     */
    private Integer cost ;
    /**
     * 库房编码--单位--包装规格....
     */
}
