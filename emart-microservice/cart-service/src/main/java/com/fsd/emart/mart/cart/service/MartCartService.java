package com.fsd.emart.mart.cart.service;

import java.util.List;

import com.fsd.emart.common.bean.GoodInfo;
import com.fsd.emart.mart.cart.bean.CartData;

public interface MartCartService {

    public List<GoodInfo> getCartList(String accountId);

    public void updateCartList(String accountId, List<CartData> list);
}
