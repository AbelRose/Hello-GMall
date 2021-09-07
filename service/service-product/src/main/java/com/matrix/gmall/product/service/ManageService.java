package com.matrix.gmall.product.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.matrix.gmall.model.product.*;

import java.util.List;

/**
 *
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
     * @return List<BaseCategory1>
     */
   List<BaseCategory1> getBaseCategory1();

    /**
     * 根据以及分类Id获取二级分类数据
     * @param category1Id category1Id
     * @return List<BaseCategory2>
     */
   List<BaseCategory2> getBaseCategory2(Long category1Id);

    /**
     * 根据以及分类Id获取三级分类数据
     * @param category2Id category2Id
     * @return List<BaseCategory3>
     */
    List<BaseCategory3> getBaseCategory3(Long category2Id);

    /**
     * 根据分类Id获取平台属性列表
     * @param category1Id category1Id
     * @param category2Id category2Id
     * @param category3Id category3Id
     * @return List<BaseAttrInfo>
     */
    List<BaseAttrInfo> getBaseAttrInfoList(Long category1Id, Long category2Id, Long category3Id);

    /**
     * 保存平台属性
     * @RequestBody -> 将Json数据转换成Java对象
     * @param baseAttrInfo baseAttrInfo
     */
    void saveAttrInfo(BaseAttrInfo baseAttrInfo);

    /**
     * 根据平台属性Id 获取平台属性值集合
     * @param attrId attrId
     * @return List<BaseAttrValue>
     */
    List<BaseAttrValue> getAttrValueList(Long attrId);

    /**
     * 提前判断一下属性值是否有属性
     * @param attrId attrId
     * @return BaseAttrInfo
     */
    BaseAttrInfo getBaseAttrInfo(Long attrId);

    /**
     * 根据三级分类Id 获取SpuInfo集合数据
     * @param spuInfo spuInfo拿到三级分类的Id
     * @param spuInfoPage 分页
     * @return IPage<SpuInfo>
     */
    IPage<SpuInfo> getSpuInfoList(SpuInfo spuInfo, Page<SpuInfo> spuInfoPage);
}
