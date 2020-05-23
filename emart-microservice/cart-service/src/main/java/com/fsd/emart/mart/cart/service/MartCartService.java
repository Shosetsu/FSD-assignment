package com.fsd.emart.mart.cart.service;

import java.util.List;

import com.fsd.emart.common.bean.GoodInfo;

public interface MartCartService {

    public List<GoodInfo> getCartList(String accountId);

    public void updateCartList(String accountId, List<String> list);
}
