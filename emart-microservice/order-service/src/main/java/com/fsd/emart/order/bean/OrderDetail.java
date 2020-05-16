package com.fsd.emart.order.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;

import com.fsd.emart.common.constans.Constants;
import com.fsd.emart.common.entity.ItemInfo;
import com.fsd.emart.common.entity.OrderInfo;
import com.fsd.emart.common.util.StringUtil;

import lombok.Data;

@Data
public class OrderDetail implements Serializable {

    /** UID */
    private static final long serialVersionUID = -8758930923709077235L;

    private String orderId;
    private String seller;
    private String buyer;
    private String name;
    private String[] category;
    private String manufacturer;
    private BigDecimal price;
    private BigInteger count;
    private BigDecimal amount;
    private Timestamp timestamp;
    private String goodId;

    public static OrderDetail getDetailFromEntites(OrderInfo order, ItemInfo item) {
        OrderDetail detail = new OrderDetail();
        detail.setOrderId(order.getOrderId());
        detail.setSeller(order.getSellerId());
        detail.setBuyer(order.getBuyerId());
        detail.setName(item.getName());
        detail.setCategory(StringUtil.splitString(item.getCategory(), Constants.COMMA));
        detail.setManufacturer(item.getManufacturer());
        detail.setPrice(order.getPrice());
        detail.setCount(order.getCount());
        detail.setAmount(order.getAmount());
        detail.setTimestamp(order.getOrderTime());
        detail.setGoodId(order.getItemId());
        return detail;
    }
}
