package com.fsd.emart.mart.cart.service.impl;

import javax.annotation.Resource;

import com.fsd.emart.common.dao.CartDao;
import com.fsd.emart.mart.cart.service.MartCartService;

public class MartCartServiceImpl implements MartCartService {

	@Resource
	private CartDao cartDao;

	@Override
	public String[] getCartList(String accountId) {
		// TODO
		return null;
	}

	@Override
	public void updateCartList(String accountId, String[] cartList) {
		// TODO
	}

}
