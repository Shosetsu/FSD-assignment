package com.fsd.emart.mart.query.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.fsd.emart.common.dao.CategoryDao;
import com.fsd.emart.common.dao.ItemDao;
import com.fsd.emart.common.dao.ManufacturerDao;
import com.fsd.emart.mart.query.bean.FilterConditions;
import com.fsd.emart.mart.query.bean.GoodInfo;
import com.fsd.emart.mart.query.service.MartQueryService;

public class MartQueryServiceImpl implements MartQueryService {
	
	@Resource
	private CategoryDao categoryDao;
	
	@Resource
	private ManufacturerDao manufacturerDao;
	
	@Resource
	private ItemDao itemDao;

	@Override
	public String[] getCategoryList() {
		// TODO
		return null;
	}

	@Override
	public String[] getManufacturerList() {
		// TODO
		return null;
	}

	@Override
	public GoodInfo queryItemDetail(String itemId) {
		// TODO
		return null;
	}

	@Override
	public List<GoodInfo> queryItems(FilterConditions filter) {
		// TODO
		return null;
	}

}
