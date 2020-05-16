package com.fsd.emart.common.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

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
    public int status;
}
