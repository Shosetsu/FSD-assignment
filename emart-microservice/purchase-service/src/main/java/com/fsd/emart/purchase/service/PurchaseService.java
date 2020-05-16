package com.fsd.emart.purchase.service;

import java.util.List;

import com.fsd.emart.purchase.bean.PurchaseItemInfo;

public interface PurchaseService {

    public void purchase(List<PurchaseItemInfo> purchaseList, String accountId);

}
