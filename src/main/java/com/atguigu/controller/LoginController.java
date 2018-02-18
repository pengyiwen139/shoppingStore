package com.atguigu.controller;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.bean.t_mall_shoppingcar;
import com.atguigu.bean.t_mall_user_account;
import com.atguigu.mapper.LoginMapper;
import com.atguigu.server.UserServer;
import com.atguigu.service.CartService;
import com.atguigu.service.SkuListService;
import com.atguigu.util.MyJsonUtil;
import com.atguigu.util.MyPropertiesUtil;
import com.atguigu.util.SelectUserUtil;


@Controller
public class LoginController {

	@Autowired
	private LoginMapper loginmapper;
	@Autowired
	private CartService cartService;
	@Autowired
	private UserServer userServer;
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		return "login";
	}
	
	@RequestMapping("/to_login")
	public String to_login() {
		
		return "login";
	}
	
	@RequestMapping("/do_login")
	public String login(String dataSource_type,@CookieValue(value="list_cart_cookie",required=false) String list_cart_cookie, HttpServletResponse response,HttpSession session,ModelMap map,t_mall_user_account t_mall_user_account) {
		//不久之后，下一行代码将会变成调用其他项目的某个实现登陆的方法，对另外一个数据库进行访问，完成登陆操作
		t_mall_user_account user = null;
		//选择数据源
		if(dataSource_type.equals("1")){
			 user = userServer.login(t_mall_user_account);
		}else{
			 user = userServer.login2(t_mall_user_account);
		}
		
		if(user==null) {
			return "to_login.do";
		}else {
			session.setAttribute("user", user);
			String yhNch=user.getYhNch();
			Cookie cookie1 = null;
			System.out.println(user);
			try {
				cookie1 = new Cookie("yhNch", URLEncoder.encode(yhNch, "UTF-8"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			cookie1.setMaxAge(60 * 60 * 24);
			response.addCookie(cookie1);
			
			//登陆的时候合并购物车，把cookie里面的购物车数据添加到当前用户的db里面去，然后把cookies清除，确保用户的隐私
//			然后把同步之后的db数据放到session中
//			在这里，我把合并购物车的步骤封装在一个方法里面，登陆的时候调用这个方法，
//			需要的数据：session，user,cookie,response,db
			List<t_mall_shoppingcar> userCarInDB = cartService.getUserDBbyId(user.getId());
			mergeCookies2SBdb(list_cart_cookie,user,session,response,userCarInDB);
		}
		return "redirect:/sale_index.do";
	}
	
	private void mergeCookies2SBdb(String list_cart_cookie,t_mall_user_account user,HttpSession session,HttpServletResponse response,List<t_mall_shoppingcar> userCarInDB){
		//如果此用户的cart的db为空
		List<t_mall_shoppingcar> list_cart = new ArrayList<t_mall_shoppingcar>();
		if(userCarInDB==null || userCarInDB.size()==0){
			//如果此用户当前浏览器的cookie也为空
			if(StringUtils.isBlank(list_cart_cookie)){
				//不做任何操作
			}else{
				//如果当前cookie不为空
				//把cookie转换为List《。car》对象
				list_cart = MyJsonUtil.json_to_list(list_cart_cookie, t_mall_shoppingcar.class);
				//把cookie对象插入到db中
				for (t_mall_shoppingcar car : list_cart) {
					car.setYhId(user.getId());
					cartService.addCart(car);
				}
			}
		}else{
			//ifdb不为空
			if(StringUtils.isBlank(list_cart_cookie)){
				//如果cookie为空
				
			}else{
				//如果cookie不为空
				list_cart = MyJsonUtil.json_to_list(list_cart_cookie, t_mall_shoppingcar.class);
				for (t_mall_shoppingcar car : list_cart) {
					boolean if_new_cart = if_new_cart(userCarInDB,car);
					if(if_new_cart){
					//如果不存在相同的商品
						car.setYhId(user.getId());
						cartService.addCart(car);
					}else{
						car.setYhId(user.getId());
						cartService.update_cart(car);
					}
				}
			}
		}
		//两种情况操作完毕,ranhou就是清空cookie，保证用户隐私，把db数据同步到session中
		response.addCookie(new Cookie("list_cart_cookie", ""));
		session.setAttribute("list_cart_session", cartService.selectAll(user.getId()));
		
	}
	
	private boolean if_new_cart(List<t_mall_shoppingcar> list_cart, t_mall_shoppingcar cart) {
		for (t_mall_shoppingcar oneOfCart : list_cart) {
			if (oneOfCart.getSkuId() == cart.getSkuId()) {
				return false;
			}
		}
		return true;
	}
	
}
