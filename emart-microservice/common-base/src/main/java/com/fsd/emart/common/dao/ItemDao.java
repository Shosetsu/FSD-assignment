package com.fsd.emart.common.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.fsd.emart.common.entity.ItemInfo;

@Repository
public interface ItemDao extends JpaRepository<ItemInfo, String>, JpaSpecificationExecutor<ItemInfo> {

    public List<ItemInfo> findByOwnerIdOrderByUpdateTimeDesc(String ownerId);

}
