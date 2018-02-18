package com.atguigu.mapper;

import com.atguigu.bean.t_mall_user_account;

public interface LoginMapper {

	t_mall_user_account selectLogin(t_mall_user_account user);
	
}
