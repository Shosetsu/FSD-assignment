package com.fsd.emart.common.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.fsd.emart.common.entity.CartInfo;

@Repository
public interface CartDao extends JpaRepository<CartInfo, String>, JpaSpecificationExecutor<CartInfo> {}
