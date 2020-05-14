package com.fsd.emart.seller.service;

import java.util.List;

import com.fsd.emart.common.bean.GoodInfo;
import com.fsd.emart.seller.bean.SalesOverviewInfo;

public interface SellerManagementService {

	public Boolean isSeller(String accountId);

	public List<GoodInfo> getSalesList(String accountId);

	public SalesOverviewInfo getSalesOverviewByMonth(String yyyyMM);

	public void saveSalesItem(GoodInfo info);

	public void changeSalesItemStatus(String itemId, String status);

}
