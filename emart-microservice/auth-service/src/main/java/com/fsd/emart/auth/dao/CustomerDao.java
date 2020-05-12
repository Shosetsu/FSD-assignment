package com.fsd.emart.auth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.fsd.emart.auth.entity.CustomerInfo;

@Repository
public interface CustomerDao extends JpaRepository<CustomerInfo, String>, JpaSpecificationExecutor<CustomerInfo> {
}
