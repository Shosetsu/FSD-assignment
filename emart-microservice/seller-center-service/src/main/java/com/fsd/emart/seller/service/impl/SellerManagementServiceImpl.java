package com.fsd.emart.seller.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.fsd.emart.common.bean.GoodInfo;
import com.fsd.emart.common.dao.ItemDao;
import com.fsd.emart.common.dao.ManufacturerDao;
import com.fsd.emart.seller.bean.SalesOverviewInfo;
import com.fsd.emart.seller.service.SellerManagementService;

public class SellerManagementServiceImpl implements SellerManagementService {

	@Resource
	private ItemDao itemDao;
	
	@Resource
	private ManufacturerDao manufacturerDao;
	
	@Override
	public Boolean isSeller(String accountId) {
		// TODO
		return null;
	}

	@Override
	public List<GoodInfo> getSalesList(String accountId) {
		// TODO
		return null;
	}

	@Override
	public SalesOverviewInfo getSalesOverviewByMonth(String yyyyMM) {
		// TODO
		return null;
	}

	@Override
	public void saveSalesItem(GoodInfo info) {
		// TODO

	}

	@Override
	public void changeSalesItemStatus(String itemId, String status) {
		// TODO

	}

}
