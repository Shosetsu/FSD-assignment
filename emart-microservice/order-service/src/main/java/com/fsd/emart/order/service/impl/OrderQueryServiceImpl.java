package com.fsd.emart.order.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.fsd.emart.common.dao.ItemDao;
import com.fsd.emart.common.dao.OrderDao;
import com.fsd.emart.order.bean.OrderDetail;
import com.fsd.emart.order.service.OrderQueryService;

public class OrderQueryServiceImpl implements OrderQueryService {

	@Resource
	private OrderDao orderDao;

	@Resource
	private ItemDao itemDao;
	
	
	@Override
	public OrderDetail queryOrderDetail(String orderId) {
		// TODO
		return null;
	}

	@Override
	public List<OrderDetail> queryOrder(String accountId, Integer queryStartIndex) {
		// TODO
		return null;
	}

}
