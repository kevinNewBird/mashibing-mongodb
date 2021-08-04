package com.mashibing.mongodb.util;

/**
 * description  RandomUtil <BR>
 * <p>
 * author: zhao.song
 * date: created in 15:03  2021/8/4
 * company: TRS信息技术有限公司
 * version 1.0
 */
public class RandomUtil {

    public static int getNum(int start, int end) {
        return (int) (Math.random() * (end - start + 1) + start);
    }
}
