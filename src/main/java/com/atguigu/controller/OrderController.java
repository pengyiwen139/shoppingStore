package com.atguigu.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.atguigu.bean.OBJECT_T_MALL_FLOW;
import com.atguigu.bean.OBJECT_T_MALL_ORDER;
import com.atguigu.bean.T_MALL_ADDRESS;
import com.atguigu.bean.T_MALL_FLOW;
import com.atguigu.bean.T_MALL_ORDER_INFO;
import com.atguigu.bean.t_mall_shoppingcar;
import com.atguigu.bean.t_mall_user_account;
import com.atguigu.server.AddressServer;
import com.atguigu.service.CartService;
import com.atguigu.service.OrderServiceInf;
import com.atguigu.util.OverSaleException;

@SessionAttributes("order")
@Controller
public class OrderController {

	@Autowired
	private AddressServer addressServer;

	@Autowired
	private CartService cartService;
	
	@Autowired
	private OrderServiceInf orderServiceInf;

	@RequestMapping("/save_order")
	public String save_order(HttpSession session,ModelMap map,@ModelAttribute("order")OBJECT_T_MALL_ORDER order){
		int address_id =(Integer) session.getAttribute("address_id");
		System.out.println(address_id);
		
		t_mall_user_account user = (t_mall_user_account)session.getAttribute("user");
		//通过地址id保存订单信息
		T_MALL_ADDRESS address = addressServer.getAddressByAddressId(address_id);
		orderServiceInf.save_order(order, address);

		//重新同步session里面购物车的数据
		List<t_mall_shoppingcar> selectAll = cartService.selectAll(user.getId());
		session.setAttribute("list_cart_session", selectAll);
		for (t_mall_shoppingcar t_mall_shoppingcar : selectAll) {
			System.out.println(t_mall_shoppingcar);
		}
		return "redirect:/goto_pay.do";
	}
	
	//点击支付触发的操作
	@RequestMapping("goto_pay")
	public String goto_pay() {

		// 调用支付服务返回的页面
		return "sale_pay";
	}
	
	@RequestMapping("pay_after")
	public String pay_after(@ModelAttribute("order") OBJECT_T_MALL_ORDER order) {

		// 调用支付服务成功后调用订单通知业务
		try {
			orderServiceInf.pay_order(order);
		} catch (OverSaleException e) {
			e.printStackTrace();
			return "overSale_error";
		}
		return "sale_success";
	}
	
	
	@RequestMapping("/check_order")
	public String check_order(HttpSession session,ModelMap map){
		//判断用户是否登陆
		//从session中获取用户信息
		t_mall_user_account user = (t_mall_user_account)session.getAttribute("user");
		if(user==null){
			return "redirect:/to_login.do";
		}else{
			//已经登陆
			//从session中获取放进去的carts——list
			List<t_mall_shoppingcar> carts_list = (List<t_mall_shoppingcar>)session.getAttribute("list_cart_session");
			//自定义一个总订单
			OBJECT_T_MALL_ORDER order = new OBJECT_T_MALL_ORDER();
			order.setZje(getSum(carts_list));//根据bigdecimal得到总金额数
			order.setYh_id(user.getId());
			order.setJdh(0);
			
			
			// 根据库存拆单(这里我们根据库存地址拆单，就当作我们是在个一个小型的地方销售企业做的线上网站，仓库也就只有本省的几个)
			//为了让拆出来的订单去重，所以我在这里就不用有序可重复的list集合。而去用无序不可重复的set集合
			Set<String> set_kcdz = new HashSet<String>();
			
			//循环购物车list，把所有的库存地址set到这个Set集合里面去
			for (t_mall_shoppingcar cart : carts_list) {
				set_kcdz.add(cart.getKcdz());
			}
			
			// 循环库存地址，将订单中的商品数据按照库存地址放入库存包裹
			List<OBJECT_T_MALL_FLOW> list_flow = new ArrayList<OBJECT_T_MALL_FLOW>();
			//得到set_kcdz的迭代器
			Iterator<String> iterator = set_kcdz.iterator();
			while(iterator.hasNext()){
				//通过迭代器迭代出每一个地址
				String kcdz = iterator.next();
				//将迭代出来的库存地址存放到库存包裹中
				OBJECT_T_MALL_FLOW flow = new OBJECT_T_MALL_FLOW();
				flow.setPsfsh("翠西快递");
				flow.setMqdd("商品未出库");
				flow.setYh_id(user.getId());
				List<T_MALL_ORDER_INFO> list_info = new ArrayList<T_MALL_ORDER_INFO>();
				for (t_mall_shoppingcar cart : carts_list) {
					//判断set里面迭代出来的shdz是否和session里面迭代出来的shdz相同，同时要让这个商品被选中
					if(cart.getShfxz().equals("1") && cart.getKcdz().equals(kcdz)){
						//new一个订单信息对象，把购物车里的库存地址与之相同的对象的信息set到订单对象中
						T_MALL_ORDER_INFO order_info = new T_MALL_ORDER_INFO();
						System.out.println("订单id："+cart.getId());
						order_info.setGwch_id(cart.getId());
						System.out.println(cart.getId());
						order_info.setShp_tp(cart.getShpTp());
						order_info.setSku_id(cart.getSkuId());
						order_info.setSku_jg(cart.getSkuJg());
						order_info.setSku_kcdz(kcdz);
						order_info.setSku_mch(cart.getSkuMch());
						order_info.setSku_shl(cart.getTjshl());
						//把饱含购物车单个商品信息的订单信息对象放到订单信息list对象中
						list_info.add(order_info);
					}
				}
				
				//订单信息list把根据地址拆分的订单成功接收完成之后，把这个订单信息list放到当前地址的库存包裹中，请看65行
				flow.setList_info(list_info);
				//把迭代好了所有信息的的包裹存放到list_flow中，
				list_flow.add(flow);
			}
			order.setList_flow(list_flow);
			List<T_MALL_ADDRESS> selectAllAddressByYHId = addressServer.SelectAllAddressByYHId(user.getId());
			for (T_MALL_ADDRESS t_MALL_ADDRESS : selectAllAddressByYHId) {
//				System.out.println("**这里为什么用户地址没有获取到**"+t_MALL_ADDRESS.getYh_dz());
				t_MALL_ADDRESS.setId(9);
				System.out.println("**这里为什么用户地址没有获取到**"+t_MALL_ADDRESS.getId());
				session.setAttribute("address_id", t_MALL_ADDRESS.getId());
			}
			
			map.put("order", order);
			map.put("list_address", selectAllAddressByYHId);
		}
		return "check_order";
	}
	
	private BigDecimal getSum(List<t_mall_shoppingcar> list_carts){
		BigDecimal sum = new BigDecimal("0");
		for (t_mall_shoppingcar cart : list_carts) {
			if(cart.getShfxz().equals("1")){
				sum = sum.add(new BigDecimal(cart.getHj()+""));
			}
		}
		return sum;
	}
	
	
	
}
