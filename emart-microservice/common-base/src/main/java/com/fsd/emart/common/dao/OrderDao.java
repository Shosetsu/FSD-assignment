package com.fsd.emart.common.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.fsd.emart.common.entity.OrderInfo;

@Repository
public interface OrderDao extends JpaRepository<OrderInfo, String>, JpaSpecificationExecutor<OrderInfo> {

    public List<OrderInfo> findAllBySellerIdOrBuyerIdOrderByOrderTimeDesc(String sellerId, String buyerId);

}
