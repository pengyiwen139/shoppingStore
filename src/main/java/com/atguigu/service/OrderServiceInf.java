package com.atguigu.service;

import com.atguigu.bean.OBJECT_T_MALL_ORDER;
import com.atguigu.bean.T_MALL_ADDRESS;
import com.atguigu.util.OverSaleException;

public interface OrderServiceInf {

	void save_order(OBJECT_T_MALL_ORDER order, T_MALL_ADDRESS address);
	
	void pay_order(OBJECT_T_MALL_ORDER order) throws OverSaleException;
	
	long getKcById(int sku_id);

}
