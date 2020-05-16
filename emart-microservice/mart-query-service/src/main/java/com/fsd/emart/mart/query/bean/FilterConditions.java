package com.fsd.emart.mart.query.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.lang.Nullable;

import lombok.Data;

@Data
public class FilterConditions implements Serializable {

    /** UID */
    private static final long serialVersionUID = 152532627781L;

    @Nullable
    private String keyword;
    @Nullable
    private String category;
    @Nullable
    private String manufacturer;
    @Nullable
    private BigDecimal priceF;
    @Nullable
    private BigDecimal priceT;

}
