package com.fsd.emart.order.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import com.fsd.emart.common.dao.ItemDao;
import com.fsd.emart.common.dao.OrderDao;
import com.fsd.emart.common.entity.OrderInfo;
import com.fsd.emart.common.exception.ApplicationException;
import com.fsd.emart.order.bean.OrderDetail;
import com.fsd.emart.order.service.OrderQueryService;

public class OrderQueryServiceImpl implements OrderQueryService {

    @Resource
    private OrderDao orderDao;

    @Resource
    private ItemDao itemDao;

    @Override
    public OrderDetail queryOrderDetail(String orderId, String accountId) {
        Optional<OrderInfo> order = orderDao.findById(orderId);

        // Authorization Check - part.2
        if (order.isPresent()
            && !(order.get().getBuyerId().equals(accountId) || order.get().getSellerId().equals(accountId))) {
            throw new ApplicationException("Not Found");
        }

        if (!order.isPresent()) {
            throw new ApplicationException("Not Found");
        }

        return OrderDetail.getDetailFromEntites(order.get(), itemDao.getOne(order.get().getItemId()));
    }

    @Override
    public List<OrderDetail> queryOrder(String accountId, Integer startRow) {
        List<OrderDetail> list = new ArrayList<>();

        Integer endRow = startRow + 50;

        List<OrderInfo> queryList = orderDao.findAllBySellerIdOrBuyerIdOrderByOrderTimeDesc(accountId, accountId);

        if (startRow < queryList.size()) {
            endRow = Math.min(endRow, queryList.size());

            for (; startRow < endRow; startRow++) {
                list.add(OrderDetail.getDetailFromEntites(queryList.get(startRow),
                    itemDao.getOne(queryList.get(startRow).getItemId())));
            }
        }
        return list;
    }

}
