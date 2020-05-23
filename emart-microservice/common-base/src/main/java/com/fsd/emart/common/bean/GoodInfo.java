package com.fsd.emart.common.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import com.fsd.emart.common.constants.Constants;
import com.fsd.emart.common.entity.ItemInfo;
import com.fsd.emart.common.util.StringUtil;

import lombok.Data;

@Data
public class GoodInfo {
    public String id;
    public String name;
    public String manufacturer;
    public List<String> category;
    public String detail;
    public BigDecimal price;
    public Integer stock;
    public String owner;
    public Timestamp createdDate;
    public Integer status;

    public static GoodInfo getInfoFromEntity(ItemInfo entity) {
        GoodInfo good = new GoodInfo();
        good.setId(entity.getItemId().toString());
        good.setName(entity.getName());
        good.setManufacturer(entity.getManufacturer());
        good.setCategory(Arrays.asList(StringUtil.splitString(entity.getCategory(), Constants.COMMA)));
        good.setDetail(entity.getDetail());
        good.setPrice(entity.getPrice());
        good.setStock(entity.getStock().intValue());
        good.setOwner(entity.getOwnerId());
        good.setCreatedDate(entity.getUpdateTime());
        good.setStatus(entity.getStatus());
        return good;
    }

}
