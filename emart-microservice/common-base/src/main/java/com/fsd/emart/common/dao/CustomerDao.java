package com.fsd.emart.common.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.fsd.emart.common.entity.CustomerInfo;

@Repository
public interface CustomerDao extends JpaRepository<CustomerInfo, String>, JpaSpecificationExecutor<CustomerInfo> {

    Optional<CustomerInfo> findByEmail(String email);
}
