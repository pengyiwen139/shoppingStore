package com.atguigu.mapper;

import java.util.List;
import java.util.Map;

import com.atguigu.bean.DETAIL_t_mall_sku;
import com.atguigu.bean.Model_sku;
import com.atguigu.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.bean.t_mall_sku;


public interface AttrListMapper {
	
	//根据指定的class_2_id获得attr_list
	List<OBJECT_T_MALL_ATTR> selectAttrByClass_2_id(int class_2_id);

	//跳到单个商品界面的时候需要调用的商品分类属性方法
	List<t_mall_sku> select_skus_by_spuId(int spuId);

	//跳到单个商品界面的时候需要调用的查询单个商品的细节属性的方法
	DETAIL_t_mall_sku select_detail_sku_by_skuId_spuId(Map<Object, Object> map);

}
