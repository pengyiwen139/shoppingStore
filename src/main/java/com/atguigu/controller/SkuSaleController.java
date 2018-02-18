package com.atguigu.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.bean.DETAIL_t_mall_sku;
import com.atguigu.bean.Model_sku;
import com.atguigu.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.bean.t_mall_product;
import com.atguigu.bean.t_mall_sku;
import com.atguigu.service.SkuListService;

@Controller
public class SkuSaleController {

	@Autowired
	private SkuListService skuListService;

	@RequestMapping("/goto_sku_detail")
	private String goto_sku_detail(int id, int spuId, ModelMap map) {
		//按条件查询skulist的逻辑操作
		DETAIL_t_mall_sku detail_sku = skuListService.select_detail_sku_by_skuId_spuId(id,spuId);
		List<t_mall_sku> sku_list = skuListService.select_skus_by_spuId(spuId);
		map.put("sku_list", sku_list);
		map.put("detail_sku", detail_sku);
		return "sale_search_detail";
	}
	
	@RequestMapping("/getCheckedSpuList")
	private String getCheckedSpuList(ModelMap map) {
		//按条件查询skulist的逻辑操作
		return "sale_skuByShuXin_inner";
	}
	
//	根据flbh2的sku列表查询
	@RequestMapping("/goto_sku_list")
	private String save_sku(ModelMap map ,int class_2_id) {

		//根据指定的class_2_id获得attr_list
		List<OBJECT_T_MALL_ATTR> selectAttrByClass_2_id = skuListService.selectAttrByClass_2_id(class_2_id);

		//根据指定的class_2_id获得当前分类的所有sku
		List<Model_sku> model_sku_list = skuListService.selectSkusByClass2id(class_2_id);
		
//		放到modelmap里供前台页面迭代展示出来
		map.put("attr_list", selectAttrByClass_2_id);
		map.put("model_sku_list", model_sku_list);
		return "sale_sku_list";
	}
		
}