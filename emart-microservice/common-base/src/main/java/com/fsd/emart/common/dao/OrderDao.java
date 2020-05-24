package com.fsd.emart.common.dao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fsd.emart.common.entity.OrderInfo;

@Repository
public interface OrderDao extends JpaRepository<OrderInfo, String>, JpaSpecificationExecutor<OrderInfo> {

    public List<OrderInfo> findBySellerIdOrBuyerIdOrderByOrderTimeDesc(String sellerId, String buyerId);

    public List<OrderInfo> findBySellerId(String sellerId);

    @Query(
        value = "select sum(t.count), sum(t.amount) from OrderInfo t where t.sellerId=:sellerId and t.orderTime between :start and :end")
    public Object calcTermReport(@Param("sellerId") String sellerId, @Param("start") Timestamp start,
        @Param("end") Timestamp end);

    @Query(value = "select sum(t.count), sum(t.amount) from OrderInfo t where t.sellerId=:sellerId")
    public Object calcTermReport(@Param("sellerId") String sellerId);
}
