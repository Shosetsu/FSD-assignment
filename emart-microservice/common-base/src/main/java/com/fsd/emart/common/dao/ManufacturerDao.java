package com.fsd.emart.common.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.fsd.emart.common.entity.ManufacturerData;

@Repository
public interface ManufacturerDao
    extends JpaRepository<ManufacturerData, String>, JpaSpecificationExecutor<ManufacturerData> {

    public Optional<ManufacturerData> findByName(String name);

}
