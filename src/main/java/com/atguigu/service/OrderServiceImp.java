package com.atguigu.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.OBJECT_T_MALL_FLOW;
import com.atguigu.bean.OBJECT_T_MALL_ORDER;
import com.atguigu.bean.T_MALL_ADDRESS;
import com.atguigu.bean.T_MALL_ORDER_INFO;
import com.atguigu.mapper.OrderMapper;
import com.atguigu.util.MyDateUtil;
import com.atguigu.util.OverSaleException;

@Service
public class OrderServiceImp implements OrderServiceInf {

	@Autowired
	private OrderMapper orderMapper;

	public void save_order(OBJECT_T_MALL_ORDER order, T_MALL_ADDRESS address) {
		// 保存订单表，生成主键
		// shhr 收件人,
		// zje 总金额,
		// jdh 进度号,
		// yh_id 用户id,
		// dzh_id 地址id,
		// dzh_mch 地址名称
		Map<Object, Object> order_map = new HashMap<Object, Object>();
		order.setJdh(1);
		order_map.put("order", order);
		order_map.put("address", address);
		orderMapper.insert_order(order_map);

		// 购物车
		List<Integer> list_cart_id = new ArrayList<Integer>();

		// 循环物流包裹，生成主键
		List<OBJECT_T_MALL_FLOW> list_flow = order.getList_flow();
		for (int i = 0; i < list_flow.size(); i++) {
			// 根据订单id保存物流包裹
			// psfsh 配送方式,
			// yh_id 用户id,
			// dd_id 订单id,
			// mqdd 目前地点,
			// mdd 目的地
			Map<Object, Object> flow_map = new HashMap<Object, Object>();
			flow_map.put("dd_id", order.getId());
			list_flow.get(i).setMdd(address.getYh_dz());
			flow_map.put("flow", list_flow.get(i));
			orderMapper.insert_flow(flow_map);

			// 根据物流id保存订单信息
			List<T_MALL_ORDER_INFO> list_info = list_flow.get(i).getList_info();
			Map<Object, Object> info_map = new HashMap<Object, Object>();

			info_map.put("list_info", list_info);
			info_map.put("dd_id", order.getId());
			info_map.put("flow_id", list_flow.get(i).getId());
			orderMapper.insert_infos(info_map);

			for (int j = 0; j < list_info.size(); j++) {
				list_cart_id.add(list_info.get(j).getGwch_id());
			}
		}

		// 根据保存的订单信息，删除购物车数据
		orderMapper.delete_shoppingCart(list_cart_id);
	}

	public void pay_order(OBJECT_T_MALL_ORDER order) throws OverSaleException {

		// 修改库存状态
		List<OBJECT_T_MALL_FLOW> list_flow = order.getList_flow();
		for (OBJECT_T_MALL_FLOW flow : list_flow) {
			// 修改物流状态，生成物流信息
			List<T_MALL_ORDER_INFO> list_info = flow.getList_info();
			for (T_MALL_ORDER_INFO info : list_info) {
				int sku_id = info.getSku_id();
				//根据skuId查询到当前循环出来的库存的剩余数量
				long kc = getKcById(sku_id);//调用下面的方法去数据库里面查询kc
				//如果商品购买的数量小于当前库存，那就减少库存，增加销量
				
				if(true){
//				if(kc>=info.getSku_shl()){
					//减少库存
					orderMapper.update_kc(info);
				}else{
					//库存不足，对此包裹的操作进行回滚
					throw new OverSaleException("over sale");
				}
			}
			flow.setLxfsh("3399620");
			flow.setPsshj(MyDateUtil.getMyDate(1));//本来是调用物流接口
			flow.setPsmsh("正在出库中");//本来是调用物流接口
			flow.setYwy("老佟");
			orderMapper.update_flow(flow);
		}
		// 修改订单状态
		order.setJdh(3);
		order.setYjsdshj(MyDateUtil.getMyDate(3));//本来是调用物流接口
		orderMapper.update_order(order);
	}

	//封装得到库存的方法
	public long getKcById(int sku_id){
		long kc = 0l;
		//库存查询操作，需要保证不会发生脏读的可能，因为发生脏读的话，就有可能超卖的问题;
		//所以当我们的库存低于十个的时候，我们选择相对保险一点的悲观锁forupdate来保证数据的安全
		if(orderMapper.countKc(sku_id)==0){
			//这里就用forupdate的悲观锁
			orderMapper.select_kc_for_update(sku_id);
		}else{
			orderMapper.select_kc(sku_id);
		}
		return kc;
	}

}
