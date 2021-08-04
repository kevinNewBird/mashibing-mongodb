package com.mashibing.mongodb.constant;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * description  ShopNameEnum <BR>
 * <p>
 * author: zhao.song
 * date: created in 15:04  2021/8/4
 * company: TRS信息技术有限公司
 * version 1.0
 */
public enum ShopNameEnum {

    /**
     * 商品枚举
     */
    中国移动终端自营(1, "中国移动终端自营"),
    华为京东自营(2, "华为京东自营"),
    一加手机自营(3, "一加手机自营"),
    OPPOP自营(4, "OPPOP自营"),
    荣耀自营(5, "荣耀自营"),
    小米自营(6, "小米自营"),
    Apple产品自营(7, "Apple产品自营")
    ;

    private Integer key;
    private String value;
    public Integer getKey() {
        return key;
    }
    public String getValue() {
        return value;
    }
    ShopNameEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }
    public static String getValueByKey(Integer key) {
        return Arrays.stream(ShopNameEnum.values())
                .filter(item -> item.getKey().equals(key))
                .findFirst()
                .map(ShopNameEnum::getValue).orElse("");
    }

    public static String getRandomShopName(){
        int random = new Random().nextInt(6) + 1;
        return ShopNameEnum.getValueByKey(random);
    }

    @Test
    public void test() {
        System.out.println(ShopNameEnum.getRandomShopName());
    }
}
