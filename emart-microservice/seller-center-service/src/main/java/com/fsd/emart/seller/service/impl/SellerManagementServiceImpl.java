package com.fsd.emart.seller.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fsd.emart.common.bean.GoodInfo;
import com.fsd.emart.common.constants.Constants;
import com.fsd.emart.common.dao.ItemDao;
import com.fsd.emart.common.dao.ManufacturerDao;
import com.fsd.emart.common.dao.OrderDao;
import com.fsd.emart.common.entity.ItemInfo;
import com.fsd.emart.common.entity.ManufacturerData;
import com.fsd.emart.common.exception.ApplicationException;
import com.fsd.emart.common.exception.AuthException;
import com.fsd.emart.common.util.AuthUtil;
import com.fsd.emart.common.util.StringUtil;
import com.fsd.emart.seller.bean.SalesOverviewInfo;
import com.fsd.emart.seller.service.SellerManagementService;

@Service
public class SellerManagementServiceImpl implements SellerManagementService {

    @Resource
    private ItemDao itemDao;

    @Resource
    private OrderDao orderDao;

    @Resource
    private ManufacturerDao manufacturerDao;

    @Resource
    private AuthUtil authUtil;

    @Override
    public Boolean isSeller(String accountId) {
        return Constants.ROLE_SELLER.equals(authUtil.getAccountType(accountId));
    }

    @Override
    public List<GoodInfo> getSalesList(String accountId) {
        List<ItemInfo> list = itemDao.findByOwnerIdOrderByUpdateTimeDesc(accountId);
        List<GoodInfo> result = new ArrayList<>(list.size());
        for (ItemInfo itemInfo : list) {
            result.add(GoodInfo.getInfoFromEntity(itemInfo));
        }

        return result;
    }

    @Override
    public SalesOverviewInfo getSalesOverviewByMonth(String accountId, String dateYm) {
        boolean filterFlag = true;
        Calendar start = Calendar.getInstance();
        try {
            start.setTime(new SimpleDateFormat("yyyyMM").parse(dateYm));
        } catch (ParseException e) {
            filterFlag = false;
        }

        // query
        Object[] queryResult = null;
        if (filterFlag) {
            Calendar end = (Calendar)start.clone();
            end.add(Calendar.MONTH, 1);
            queryResult = (Object[])orderDao.calcTermReport(accountId, new Timestamp(start.getTimeInMillis()),
                new Timestamp(end.getTimeInMillis() - 1));
        } else {
            queryResult = (Object[])orderDao.calcTermReport(accountId);
        }

        SalesOverviewInfo result = new SalesOverviewInfo();
        result.setCount((BigInteger)queryResult[0]);
        result.setAmount((BigDecimal)queryResult[1]);
        return result;
    }

    @Override
    public void saveSalesItem(GoodInfo info, String accountId) {
        Optional<ItemInfo> item = Optional.empty();

        if (!StringUtil.isEmpty(info.getId())) {
            item = itemDao.findById(info.getId());
        }

        ItemInfo newInfo = new ItemInfo();

        if (item.isPresent()) {
            // check owner
            ItemInfo oldInfo = item.get();
            if (!oldInfo.getOwnerId().equals(accountId)) {
                throw new AuthException("Invalid Operation.");
            }
            newInfo.setCreateTime(oldInfo.getCreateTime());
            newInfo.setStatus(oldInfo.getStatus());
            newInfo.setItemId(oldInfo.getItemId());
            newInfo.setSoldCount(oldInfo.getSoldCount());
        } else {
            newInfo.setStatus(Constants.ITEM_STATUS_NORMAL);
            newInfo.setSoldCount(BigInteger.ZERO);

            // update manufacturer name
            if (!manufacturerDao.findByName(info.getManufacturer()).isPresent()) {
                ManufacturerData manuData = new ManufacturerData();
                manuData.setCreateUser(accountId);
                manuData.setName(info.getManufacturer());

                manufacturerDao.save(manuData);
            }
        }
        newInfo.setOwnerId(accountId);
        newInfo.setName(info.getName());
        newInfo.setCategory(String.join(Constants.COMMA, info.getCategory()));
        newInfo.setManufacturer(info.getManufacturer());
        newInfo.setDetail(info.getDetail());
        newInfo.setPrice(info.getPrice());
        newInfo.setStock(BigInteger.valueOf(info.getStock()));

        itemDao.saveAndFlush(newInfo);

    }

    @Override
    public void changeSalesItemStatus(String itemId, Integer status, String accountId, String accountType) {
        Optional<ItemInfo> item = itemDao.findById(itemId);

        // check exist
        if (!item.isPresent()) {
            throw new ApplicationException("Item not found.");
        }

        ItemInfo itemInfo = item.get();
        Integer oldStatus = itemInfo.getStatus();
        // check owner or manager
        if ((itemInfo.getOwnerId().equals(accountId) && oldStatus != Constants.ITEM_STATUS_BLOCK
            && status != Constants.ITEM_STATUS_BLOCK)
            || (Constants.ROLE_ADMIN.equals(accountType) && oldStatus != Constants.ITEM_STATUS_ARCHIVED
                && status != Constants.ITEM_STATUS_ARCHIVED)) {
            if (oldStatus == status) {
                throw new ApplicationException("No change.");
            }
            itemInfo.setStatus(status);
            itemDao.save(itemInfo);
        } else {
            throw new AuthException("Invalid Operation.");
        }

    }

}
