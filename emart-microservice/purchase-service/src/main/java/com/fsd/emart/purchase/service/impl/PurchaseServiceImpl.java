package com.fsd.emart.purchase.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fsd.emart.common.dao.ItemDao;
import com.fsd.emart.common.dao.OrderDao;
import com.fsd.emart.common.entity.ItemInfo;
import com.fsd.emart.common.entity.OrderInfo;
import com.fsd.emart.common.exception.ApplicationException;
import com.fsd.emart.common.exception.SystemException;
import com.fsd.emart.purchase.bean.PurchaseItemInfo;
import com.fsd.emart.purchase.service.PurchaseService;

@Transactional
@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Resource
    private OrderDao orderDao;

    @Resource
    private ItemDao itemDao;

    @Override
    public void purchase(List<PurchaseItemInfo> purchaseList, String accountId) {

        List<OrderInfo> orderList = new ArrayList<>();
        BigDecimal allAmount = BigDecimal.ZERO;

        for (PurchaseItemInfo info : purchaseList) {
            // get new info of Database
            Optional<ItemInfo> currentItem = itemDao.findById(info.getId());
            if (!currentItem.isPresent()) {
                throw new SystemException("Invalid item. Please refresh the page and try again.");
            }

            // check price
            if (!currentItem.get().getPrice().equals(info.getPrice())) {
                throw new ApplicationException("The item [" + currentItem.get().getName()
                    + "]'s price has changed. Please refresh the page and try again.");
            }

            // check stock
            if (currentItem.get().getStock().compareTo(BigInteger.valueOf(info.getCount())) < 0) {
                throw new ApplicationException("The item [" + currentItem.get().getName() + "] over stock.");
            }

            OrderInfo order = new OrderInfo();
            order.setBuyerId(accountId);
            order.setSellerId(currentItem.get().getOwnerId());
            order.setCount(BigInteger.valueOf(info.getCount()));
            order.setItemId(currentItem.get().getItemId());
            order.setPrice(currentItem.get().getPrice());
            order.setAmount(currentItem.get().getPrice().multiply(BigDecimal.valueOf(info.getCount())));

            orderList.add(order);

            // calc
            allAmount.add(order.getAmount());
        }

        // TODO connect out-server to completed the purchase. => allAmount

        for (PurchaseItemInfo info : purchaseList) {
            itemDao.updateSalesInfo(BigInteger.valueOf(info.getCount()), info.getId());
        }
        orderDao.saveAll(orderList);
        orderDao.flush();
    }

}
