package com.fsd.emart.purchase.service.impl;

import javax.annotation.Resource;

import com.fsd.emart.common.dao.OrderDao;
import com.fsd.emart.purchase.bean.PurchaseItemInfo;
import com.fsd.emart.purchase.service.PurchaseService;

public class PurchaseServiceImpl implements PurchaseService {

	@Resource
	private OrderDao orderDao;

	@Override
	public void purchase(PurchaseItemInfo[] purchaseList) {
		// TODO
	}

}
