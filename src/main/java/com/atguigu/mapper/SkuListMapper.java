package com.atguigu.mapper;

import java.util.List;

import com.atguigu.bean.Model_sku;

public interface SkuListMapper {
	
	//根据指定的class_2_id获得当前分类的所有sku
	List<Model_sku> selectSkusByClass2id(int class_2_id);

	
}
