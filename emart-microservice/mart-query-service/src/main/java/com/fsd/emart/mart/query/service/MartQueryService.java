package com.fsd.emart.mart.query.service;

import java.util.List;

import org.springframework.lang.Nullable;

import com.fsd.emart.mart.query.bean.FilterConditions;
import com.fsd.emart.mart.query.bean.GoodInfo;

public interface MartQueryService {

	public String[] getCategoryList();

	public String[] getManufacturerList();

	public GoodInfo queryItemDetail(String itemId);

	public List<GoodInfo> queryItems(@Nullable FilterConditions filter);
}
