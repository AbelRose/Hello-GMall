package com.matrix.gmall.cart.service;

import com.matrix.gmall.model.cart.CartInfo;

import java.util.List;

/**
 * @Author: yihaosun
 * @Date: 2021/10/28 21:00
 */
public interface CartInfoService {
    /**
     * 添加购物车的方法
     *
     * @param skuId skuId
     * @param userId userId
     * @param skuNum skuNum
     */
    void addToCart(Long skuId, String userId, Integer skuNum);

    /**
     * 根据用户(临时用户)查询购物车列表
     *
     * @param userId userId
     * @param userTempId userTempId
     * @return List<CartInfo>
     */
    List<CartInfo> getCartList(String userId, String userTempId);

    /**
     *  更新选中状态！
     * @param userId userId
     * @param isChecked isChecked
     * @param skuId skuId
     */
    void checkCart(String userId, Integer isChecked, Long skuId);

    /**
     * 删除购物项
     *
     * 注意: 不能用skuId进行删除 因为不知道是哪个用户的商品Id
     * @param skuId skuId
     * @param userId userId
     */
    void deleteCart(Long skuId, String userId);

    /**
     * 根据用户Id获取购物车{选中}列表
     *
     * @param userId userId
     * @return List<CartInfo> List<CartInfo>
     */
    List<CartInfo> getCartCheckedList(String userId);

    /**
     * 根据 userId 查询购物车最新数据
     *
     * @param userId userId
     * @return List<CartInfo>
     */
    List<CartInfo> loadCartCache(String userId);
}
