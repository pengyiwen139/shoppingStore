package com.atguigu.service;

import java.util.List;

import com.atguigu.bean.t_mall_shoppingcar;

public interface CartService {

	int countCartById(Integer yhId);

	void addCart(t_mall_shoppingcar cart);

	List<t_mall_shoppingcar> selectAll(Integer yhId);

	void update_cart(t_mall_shoppingcar t_mall_shoppingcar);

	List<t_mall_shoppingcar> getUserDBbyId(Integer id);
	
}
