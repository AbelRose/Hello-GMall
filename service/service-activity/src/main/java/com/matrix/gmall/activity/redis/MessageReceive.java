package com.matrix.gmall.activity.redis;

import com.matrix.gmall.activity.util.CacheHelper;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

/**
 * @author yihaosun
 * @date 2022/2/17 14:16
 */
@Component
public class MessageReceive {
    /**
     * 接收消息的方法
     */
    public void receiveMessage(String message) {
        System.out.println("----------收到消息了message：" + message);
        if (!StringUtils.isEmpty(message)) {
            /*
             消息格式
                skuId:0 表示没有商品
                skuId:1 表示有商品
                TODO 坑点: ！！！发送的消息：46:1 实际上是带引号的！""46:1""
             */
            message = message.replaceAll("\"", "");
            String[] split = StringUtils.split(message, ":");
            // String[] split = message.split(":");
            if (split == null || split.length == 2) {
                // put(skuId,status);
                CacheHelper.put(split[0], split[1]);
            }
        }
    }
}
