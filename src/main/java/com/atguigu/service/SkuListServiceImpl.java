package com.atguigu.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.DETAIL_t_mall_sku;
import com.atguigu.bean.Model_sku;
import com.atguigu.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.bean.t_mall_sku;
import com.atguigu.mapper.AttrListMapper;
import com.atguigu.mapper.SkuListMapper;

@Service
public class SkuListServiceImpl implements SkuListService{

	@Autowired
	private AttrListMapper attrListMapper;
	

	@Autowired
	private SkuListMapper skuListMapper;
	
	
	public List<OBJECT_T_MALL_ATTR> selectAttrByClass_2_id(int class_2_id) {
		return attrListMapper.selectAttrByClass_2_id(class_2_id);
	}

	public List<Model_sku> selectSkusByClass2id(int class_2_id) {
		return skuListMapper.selectSkusByClass2id(class_2_id);
	}

	public List<t_mall_sku> select_skus_by_spuId(int spuId) {
		return attrListMapper.select_skus_by_spuId(spuId);
	}

	public DETAIL_t_mall_sku select_detail_sku_by_skuId_spuId(int skuId, int spuId) {
		Map<Object,Object> map = new HashMap<Object,Object>();
		map.put("skuId", skuId);
		map.put("spuId", spuId);
		return 	attrListMapper.select_detail_sku_by_skuId_spuId(map);
	}

}
