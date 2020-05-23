package com.fsd.emart.mart.cart.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fsd.emart.common.bean.GoodInfo;
import com.fsd.emart.common.dao.CartDao;
import com.fsd.emart.common.dao.ItemDao;
import com.fsd.emart.common.entity.CartInfo;
import com.fsd.emart.common.entity.ItemInfo;
import com.fsd.emart.common.util.StringUtil;
import com.fsd.emart.mart.cart.bean.CartData;
import com.fsd.emart.mart.cart.service.MartCartService;

@Service
public class MartCartServiceImpl implements MartCartService {

    @Resource
    private CartDao cartDao;

    @Resource
    private ItemDao itemDao;

    @Override
    public List<GoodInfo> getCartList(String accountId) {
        Optional<CartInfo> cartInfo = cartDao.findById(accountId);

        if (!cartInfo.isPresent()) {
            return new ArrayList<>();
        }

        CartInfo cartItems = cartInfo.get();
        List<String> cartItemList = Arrays.asList(StringUtil.splitString(cartItems.getCartItems(), ","));
        String[] cartCountList = StringUtil.splitString(cartItems.getCartCounts(), ",");

        List<GoodInfo> result = new ArrayList<>();

        List<ItemInfo> itemList = itemDao.findAllById(cartItemList);

        for (ItemInfo item : itemList) {
            GoodInfo goodInfo = GoodInfo.getInfoFromEntity(item);
            goodInfo.setCount(Integer.valueOf(cartCountList[cartItemList.indexOf(item.getItemId())]));
            result.add(goodInfo);
        }

        return result;
    }

    @Override
    public void updateCartList(String accountId, List<CartData> cartList) {

        String[] items = new String[cartList.size()];
        String[] counts = new String[cartList.size()];

        for (int i = 0; i < cartList.size(); i++) {
            items[i] = cartList.get(i).getItemId();
            counts[i] = String.valueOf(cartList.get(i).getCount());
        }

        CartInfo cartInfo = new CartInfo();
        cartInfo.setAccountId(accountId);
        cartInfo.setCartItems(String.join(",", items));
        cartInfo.setCartCounts(String.join(",", counts));
        // process
        cartDao.save(cartInfo);
    }

}
