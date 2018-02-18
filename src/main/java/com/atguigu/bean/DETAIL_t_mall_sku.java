package com.atguigu.bean;

import java.util.List;

public class DETAIL_t_mall_sku extends t_mall_sku{

	private t_mall_product spu;
	private List<t_mall_product_image> list_image;
	private List<OBJECT_T_MALL_AV_NAME> list_av_name;
	public t_mall_product getSpu() {
		return spu;
	}
	public void setSpu(t_mall_product spu) {
		this.spu = spu;
	}
	public List<t_mall_product_image> getList_image() {
		return list_image;
	}
	public void setList_image(List<t_mall_product_image> list_image) {
		this.list_image = list_image;
	}
	public List<OBJECT_T_MALL_AV_NAME> getList_av_name() {
		return list_av_name;
	}
	public void setList_av_name(List<OBJECT_T_MALL_AV_NAME> list_av_name) {
		this.list_av_name = list_av_name;
	}
	
}
