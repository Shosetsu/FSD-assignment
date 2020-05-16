package com.fsd.emart.order.service;

import java.util.List;

import com.fsd.emart.order.bean.OrderDetail;

public interface OrderQueryService {

    public OrderDetail queryOrderDetail(String orderId, String accountId);

    public List<OrderDetail> queryOrder(String accountId, Integer queryStartIndex);
}
