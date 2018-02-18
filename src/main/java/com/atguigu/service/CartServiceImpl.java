package com.atguigu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.t_mall_shoppingcar;
import com.atguigu.mapper.CartDao;

@Service
public class CartServiceImpl implements CartService{

	@Autowired
	private CartDao artDao;
	
	public int countCartById(Integer yhId) {
		return artDao.countCartById(yhId);
	}

	public void addCart(t_mall_shoppingcar cart) {
		artDao.insertCart(cart);
		
	}

	public List<t_mall_shoppingcar> selectAll(Integer yhId) {
		return artDao.selectAll(yhId);
	}

	public void update_cart(t_mall_shoppingcar t_mall_shoppingcar) {
		artDao.update_cart(t_mall_shoppingcar);
	}

	public List<t_mall_shoppingcar> getUserDBbyId(Integer id) {
		return artDao.getUserDBbyId(id);
	}


}
