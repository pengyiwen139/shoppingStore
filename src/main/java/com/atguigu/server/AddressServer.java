package com.atguigu.server;

import java.util.List;

import javax.jws.WebService;

import com.atguigu.bean.T_MALL_ADDRESS;

@WebService
public interface AddressServer {

	List<T_MALL_ADDRESS> SelectAllAddressByYHId(int yh_id);
	
	void addAddress(T_MALL_ADDRESS address);
	
	T_MALL_ADDRESS getAddressByAddressId(int id);
}
