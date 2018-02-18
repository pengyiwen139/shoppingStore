package com.atguigu.service;

import java.util.List;

import com.atguigu.bean.DETAIL_t_mall_sku;
import com.atguigu.bean.Model_sku;
import com.atguigu.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.bean.t_mall_shoppingcar;
import com.atguigu.bean.t_mall_sku;


public interface SkuListService {

	List<OBJECT_T_MALL_ATTR> selectAttrByClass_2_id(int class_2_id);

	List<Model_sku> selectSkusByClass2id(int class_2_id);

	List<t_mall_sku> select_skus_by_spuId(int spuId);

	DETAIL_t_mall_sku select_detail_sku_by_skuId_spuId(int skuId, int spuId);

}
