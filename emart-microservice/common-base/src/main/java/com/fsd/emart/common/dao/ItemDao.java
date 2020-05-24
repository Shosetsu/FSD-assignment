package com.fsd.emart.common.dao;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fsd.emart.common.entity.ItemInfo;

@Repository
public interface ItemDao extends JpaRepository<ItemInfo, String>, JpaSpecificationExecutor<ItemInfo> {

    public List<ItemInfo> findByOwnerIdOrderByUpdateTimeDesc(String ownerId);

    @Modifying
    @Query(
        value = "UPDATE ItemInfo t SET t.stock = t.stock - :count, t.soldCount = t.soldCount + :count WHERE t.itemId = :itemId")
    public void updateSalesInfo(@Param("count") BigInteger count, @Param("itemId") String itemId);

}
