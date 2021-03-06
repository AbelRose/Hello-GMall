package com.matrix.gmall.product.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.matrix.gmall.model.product.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 3.1	先加载所有的一级分类数据！
 * 3.2	通过选择一级分类Id数据加载二级分类数据！
 * 3.3	通过选择二级分类数据加载三级分类数据！
 * 3.4	根据分类Id 加载 平台属性列表！
 *
 * @Author: yihaosun
 * @Date: 2021/8/31 22:16
 */
public interface ManageService {

    /**
     * 获取所有一级分类
     *
     * @return List<BaseCategory1>
     */
    List<BaseCategory1> getBaseCategory1();

    /**
     * 根据以及分类Id获取二级分类数据
     *
     * @param category1Id category1Id
     * @return List<BaseCategory2>
     */
    List<BaseCategory2> getBaseCategory2(Long category1Id);

    /**
     * 根据以及分类Id获取三级分类数据
     *
     * @param category2Id category2Id
     * @return List<BaseCategory3>
     */
    List<BaseCategory3> getBaseCategory3(Long category2Id);

    /**
     * 根据分类Id获取平台属性列表
     *
     * @param category1Id category1Id
     * @param category2Id category2Id
     * @param category3Id category3Id
     * @return List<BaseAttrInfo>
     */
    List<BaseAttrInfo> getBaseAttrInfoList(Long category1Id, Long category2Id, Long category3Id);

    /**
     * 保存平台属性
     *
     * @param baseAttrInfo baseAttrInfo
     * @RequestBody -> 将Json数据转换成Java对象
     */
    void saveAttrInfo(BaseAttrInfo baseAttrInfo);

    /**
     * 根据平台属性Id 获取平台属性值集合
     *
     * @param attrId attrId
     * @return List<BaseAttrValue>
     */
    List<BaseAttrValue> getAttrValueList(Long attrId);

    /**
     * 提前判断一下属性值是否有属性
     *
     * @param attrId attrId
     * @return BaseAttrInfo
     */
    BaseAttrInfo getBaseAttrInfo(Long attrId);

    /**
     * 根据三级分类Id 获取SpuInfo集合数据
     *
     * @param spuInfo     spuInfo拿到三级分类的Id
     * @param spuInfoPage 分页
     * @return IPage<SpuInfo>
     */
    IPage<SpuInfo> getSpuInfoList(SpuInfo spuInfo, Page<SpuInfo> spuInfoPage);

    /**
     * 获取所有的销售属性列表
     *
     * @return BaseSaleAttr
     */
    List<BaseSaleAttr> getBaseSaleAttrList();

    /**
     * 保存SpuInfo
     *
     * @param spuInfo spuInfo
     */
    void saveSpuInfo(SpuInfo spuInfo);

    /**
     * 回显spuImageList 集合数据
     * 根据spuId查询SpuImageList列表
     *
     * @param spuId spuId
     * @return List<SpuImage>
     */
    List<SpuImage> getSpuImageList(Long spuId);

    /**
     * 回显销售属性和销售属性值(在方法中有
     * // 销售属性值对象集合
     *
     * @param spuId spuId
     * @return List<SpuSaleAttr>
     * @TableField(exist = false)
     * List<SpuSaleAttrValue> spuSaleAttrValueList;
     * 获取销售属性值集合的方法)
     * <p>
     * 根据spuId 获取销售属性列表
     */
    List<SpuSaleAttr> getSpuSaleAttrList(Long spuId);

    /**
     * 保存SkuInfo数据
     *
     * @param skuInfo skuInfo
     */
    void saveSkuInfo(SkuInfo skuInfo);

    /**
     * 查询SkuInfo 带分页
     *
     * @param skuInfoPage skuInfoPage
     * @return IPage<SkuInfo>
     */
    IPage<SkuInfo> getSkuInfoList(Page<SkuInfo> skuInfoPage);

    /**
     * 根据skuId上架
     *
     * @param skuId skuId
     */
    void onSale(Long skuId);

    /**
     * 根据skuId下架
     *
     * @param skuId skuId
     */
    void cancelSale(Long skuId);

    /**
     * 根据SkuId 查询 SkuInfo 以及 SkuImageList
     *
     * @param skuId skuId
     * @param skuId skuId
     * @return SkuInfo
     * @return SkuInfo SkuInfo
     * @throws InterruptedException
     */
    SkuInfo getSkuInfo(Long skuId) throws InterruptedException;

    /**
     * 根据三级分类Id 查询 分类属性名称
     *
     * @param category3Id 注意这个category3Id 就是上面那个方法的 SkuInfo.id
     * @return BaseCategoryView
     */
    BaseCategoryView getCategoryViewByCategory3Id(Long category3Id);

    /**
     * 根据SkuId查询商品价格
     *
     * @param skuId skuId
     * @return BigDecimal
     */
    BigDecimal getSkuPrice(Long skuId);

    /**
     * 根据SkuId 和 SpuId 查询销售属性和销售属性值
     *
     * @param skuId skuId
     * @param spuId spuId
     * @return List<SpuSaleAttr>
     */
    List<SpuSaleAttr> getSpuSaleAttListCheckBySku(Long skuId, Long spuId);

    /**
     * 根据spuId获取销售属性值Id和skuId的组合数据
     * <p>
     * ！！！注意
     * 返回值可以自定义一个实体类{skuId, valueIds} 在字段比较少的时候还可以使用Map数据结构接收Map(key, value);
     * map.put("skuId", "1")
     * map.put("valueIds", "1|2")
     *
     * @param spuId spuId
     * @return Map<String, String>
     */
    Map<String, Long> getSkuIdValueIdsMap(Long spuId);

    /**
     * 获取全部分类信息 共给WebAll使用 要在service-client中将接口暴露出去
     * JSONObject
     * public class JSONObject extends JSON implements Map<String, Object>, Cloneable, Serializable, InvocationHandler {
     * <p>
     * }
     *
     * @return List<JSONObject>
     */
    List<JSONObject> getBaseCategoryList();

    /**
     * 通过品牌Id来查询品牌对应数据
     * @param tmId tmId
     * @return BaseTrademark
     */
    BaseTrademark getTrademarkByTmId(Long tmId);

    /**
     * 通过SkuId集合查询数据
     * @param skuId skuId
     * @return List<BaseAttrInfo>
     */
    List<BaseAttrInfo> getAttrList(Long skuId);
}
