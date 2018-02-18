package com.atguigu.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.bean.t_mall_shoppingcar;
import com.atguigu.bean.t_mall_user_account;
import com.atguigu.service.CartService;
import com.atguigu.util.MyJsonUtil;

@Controller
public class CarController {

	@Autowired
	private CartService cartService;

	
	
	@RequestMapping("/updateCartsStatus")
	public String updateCartsStatus(int skuId,String shfxz,@CookieValue(value = "list_cart_cookie", required = false) String list_cart_cookie,HttpSession session,ModelMap map,HttpServletResponse response){
		//从session中获取user
		t_mall_user_account user = (t_mall_user_account)session.getAttribute("user");
		//定义一个新的list_carts
		List<t_mall_shoppingcar> list_carts = new ArrayList<t_mall_shoppingcar>();
		
		if(user==null){
			//把cookie里的购物车list赋值给新定义的list——carts
			list_carts = MyJsonUtil.json_to_list(list_cart_cookie, t_mall_shoppingcar.class);
		}else{
			//把用户对应在DB里存在的list赋值给新定义的list——carts
			list_carts = (List<t_mall_shoppingcar>) session.getAttribute("list_cart_session");
		}
		for (t_mall_shoppingcar cart : list_carts) {
			//把cookie中的cart与传过来的skuid对应的cart进行配对，
			//这个设置是否选中的代码，不论user是否登陆都会执行到，所以在这里我把它提取出来
			if(cart.getSkuId()==skuId){
				cart.setShfxz(shfxz);
				if(user==null){
					//重新覆盖cookie
					Cookie cookie = new Cookie("list_cart_cookie", MyJsonUtil.list_to_json(list_carts));
					cookie.setMaxAge(60 * 60 * 24);
					response.addCookie(cookie);
				}else{
					cartService.update_cart(cart);
				}
			}
		}
		map.put("cartsPutInModel", list_carts);
		//把所有选中的商品金额合计放到modelmap中，
		map.put("cartsSum", getSum(list_carts));
		return "sale_cart_inner";
	}
	
	
	@RequestMapping("/sale_cart")
	public String sale_cart(@CookieValue(value = "list_cart_cookie", required = false) String list_cart_cookie,HttpSession session,ModelMap map){
		t_mall_user_account user = (t_mall_user_account)session.getAttribute("user");
		List<t_mall_shoppingcar> cartsPutInModel = new ArrayList<t_mall_shoppingcar>();
		if(user==null){
		//如果用户没有登陆，则mini购物车是从cookie中获得数据
			 cartsPutInModel = MyJsonUtil.json_to_list(list_cart_cookie, t_mall_shoppingcar.class);
		}else{
			 cartsPutInModel = (List<t_mall_shoppingcar>)session.getAttribute("list_cart_session");
		}
		map.put("cartsPutInModel", cartsPutInModel);
		map.put("cartsSum", getSum(cartsPutInModel));
		return "sale_cart";
	}
	
	//使用bigdecimal对购物车各类商品的合计进行相加，保证数据的安全
	private BigDecimal getSum(List<t_mall_shoppingcar> list_carts){
		BigDecimal sum = new BigDecimal("0");
		for (t_mall_shoppingcar cart : list_carts) {
			if(cart.getShfxz().equals("1")){
				sum = sum.add(new BigDecimal(cart.getHj()+""));
			}
		}
		return sum;
	}
	
	@RequestMapping("/getMiniCart")
	public String getMiniCart(@CookieValue(value = "list_cart_cookie", required = false) String list_cart_cookie,HttpSession session,ModelMap map){
		t_mall_user_account user = (t_mall_user_account)session.getAttribute("user");
		List<t_mall_shoppingcar> cartsPutInModel = new ArrayList<t_mall_shoppingcar>();
		if(user==null){
		//如果用户没有登陆，则mini购物车是从cookie中获得数据
			 cartsPutInModel = MyJsonUtil.json_to_list(list_cart_cookie, t_mall_shoppingcar.class);
		}else{
			 cartsPutInModel = (List<t_mall_shoppingcar>)session.getAttribute("list_cart_session");
		}
		map.put("cartsPutInModel", cartsPutInModel);
		return "mini_cart_list_inner";
	}
	
	@RequestMapping("/add_cart")
	public String to_index_sale(@CookieValue(value = "list_cart_cookie", required = false) String list_cart_cookie,
			t_mall_shoppingcar cart, ModelMap map, HttpServletResponse response, HttpSession session) {
		List<t_mall_shoppingcar> list_cart = new ArrayList<t_mall_shoppingcar>();
		t_mall_user_account user = (t_mall_user_account) session.getAttribute("user");
		// 调用购物车添加业务
		if (user == null) {
			// 操作cookie

			if (list_cart_cookie == null || list_cart_cookie.equals("")) {
				// cookie为空
				// 添加新数据
				list_cart.add(cart);

			} else {
				// cookie不空
				// list_cart_cookie , 转化成java购物车对象
				list_cart = MyJsonUtil.json_to_list(list_cart_cookie, t_mall_shoppingcar.class);
				// 判断是否重复
				boolean b = if_new_cart(list_cart, cart);

				if (b) {
					// 未添加过
					list_cart.add(cart);
					for (t_mall_shoppingcar t_mall_shoppingcar : list_cart) {
						System.out.println(t_mall_shoppingcar);
					}
					
				} else {
					// 添加过，更新
					for (t_mall_shoppingcar oneOfCart : list_cart) {
						if (oneOfCart.getSkuId() == cart.getSkuId()) {
							Integer tjshl = oneOfCart.getTjshl();
							oneOfCart.setTjshl(tjshl + 1);
							oneOfCart.setHj(oneOfCart.getTjshl()*oneOfCart.getSkuJg());
						}
					}
				}
			}

			// 覆盖客户端cookie，更新浏览器本地cookie数据
			Cookie cookie = new Cookie("list_cart_cookie", MyJsonUtil.list_to_json(list_cart));
			cookie.setMaxAge(60 * 60 * 24);
			response.addCookie(cookie);
		} else {
			// 从session中获得购物车数据
						list_cart = (List<t_mall_shoppingcar>) session.getAttribute("list_cart_session");

						// list_cart = shoppingCartServiceInf.get_list_cart_by_user(user);
						// 判断db是否为空
						if (list_cart == null || list_cart.size() == 0) {
							// 添加db
							cartService.addCart(cart);
							// 同步session
							list_cart = new ArrayList<t_mall_shoppingcar>();
							list_cart.add(cart);
							session.setAttribute("list_cart_session", list_cart);
						} else {
							boolean b = if_new_cart(list_cart, cart);
							if (b) {
								// 添加db
								cartService.addCart(cart);
								// 同步session
								list_cart.add(cart);
							} else {
								// 更新db
								// 同步session
								for (int i = 0; i < list_cart.size(); i++) {
									if (list_cart.get(i).getSkuId() == cart.getSkuId()) {
										list_cart.get(i).setTjshl(list_cart.get(i).getTjshl() + 1);
										list_cart.get(i).setHj(list_cart.get(i).getSkuJg() * list_cart.get(i).getTjshl());
										cartService.update_cart(list_cart.get(i));
									}
								}
							}
						}
		}
		return "shoppingSUCCESS";
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
