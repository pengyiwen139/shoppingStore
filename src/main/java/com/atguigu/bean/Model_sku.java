package com.atguigu.bean;

public class Model_sku extends t_mall_sku {

	private t_mall_product mall_product;
	private t_mall_trade_mark mall_trade_mark;

	public t_mall_product getMall_product() {
		return mall_product;
	}

	public void setMall_product(t_mall_product mall_product) {
		this.mall_product = mall_product;
	}

	public t_mall_trade_mark getMall_trade_mark() {
		return mall_trade_mark;
	}

	public void setMall_trade_mark(t_mall_trade_mark mall_trade_mark) {
		this.mall_trade_mark = mall_trade_mark;
	}

}
