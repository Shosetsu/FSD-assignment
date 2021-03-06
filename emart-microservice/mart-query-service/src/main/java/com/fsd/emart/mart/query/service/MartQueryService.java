package com.fsd.emart.mart.query.service;

import java.util.List;

import org.springframework.lang.Nullable;

import com.fsd.emart.common.bean.GoodInfo;
import com.fsd.emart.mart.query.bean.FilterConditions;

public interface MartQueryService {

    public List<String> getCategoryList();

    public List<String> getManufacturerList();

    public GoodInfo queryItemDetail(String itemId);

    public List<GoodInfo> queryItems(@Nullable FilterConditions filter, int startRow);
}
