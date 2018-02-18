package com.atguigu.server;

import javax.jws.WebService;

import com.atguigu.bean.t_mall_user_account;

@WebService
public interface UserServer {

	public t_mall_user_account login(t_mall_user_account user);
	
	public t_mall_user_account login2(t_mall_user_account user);
}