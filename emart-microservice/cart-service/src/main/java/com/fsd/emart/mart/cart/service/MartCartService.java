package com.fsd.emart.mart.cart.service;

public interface MartCartService {

	public String[] getCartList(String accountId);

	public void updateCartList(String accountId, String[] cartList);
}
