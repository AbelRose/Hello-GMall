package com.matrix.gmall.model.list;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;
import java.util.List;

/**
 * 用户检索模块的数据直接来源于索引库 而间接来源于数据库
 * 有品牌、平台属性数据、库存单元数据
 * <p>
 * Index = goods ,Type = info
 * 7.0 以后就没有type！
 *
 * @Author yihaosun
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
// ES的注解
@Document(indexName = "goods", type = "info", shards = 3, replicas = 2)
public class Goods {
    /**
     * skuId
     */
    @Id
    private Long id;

    @Field(type = FieldType.Keyword, index = false)
    private String defaultImg;

    /**
     * es 中能分词的字段，这个字段数据类型必须是 text！keyword 不分词！
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String title;

    @Field(type = FieldType.Double)
    private Double price;

    @Field(type = FieldType.Date)
    private Date createTime;

    @Field(type = FieldType.Long)
    private Long tmId;

    @Field(type = FieldType.Keyword)
    private String tmName;

    @Field(type = FieldType.Keyword)
    private String tmLogoUrl;

    @Field(type = FieldType.Long)
    private Long category1Id;

    @Field(type = FieldType.Keyword)
    private String category1Name;

    @Field(type = FieldType.Long)
    private Long category2Id;

    @Field(type = FieldType.Keyword)
    private String category2Name;

    @Field(type = FieldType.Long)
    private Long category3Id;

    @Field(type = FieldType.Keyword)
    private String category3Name;

    /**
     * 商品的热度！
     */
    @Field(type = FieldType.Long)
    private Long hotScore = 0L;

    /**
     * 平台属性集合对象
     * Nested 支持嵌套查询
     */
    @Field(type = FieldType.Nested)
    private List<SearchAttr> attrs;
}
