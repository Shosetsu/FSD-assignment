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

        String cartItems = cartInfo.get().getCartItems();
        String[] cartItemList = StringUtil.splitString(cartItems, ",");

        List<GoodInfo> result = new ArrayList<>();

        List<ItemInfo> itemList = itemDao.findAllById(Arrays.asList(cartItemList));

        for (ItemInfo item : itemList) {
            result.add(GoodInfo.getInfoFromEntity(item));
        }

        return result;
    }

    @Override
    public void updateCartList(String accountId, String[] cartList) {
        CartInfo cartInfo = new CartInfo();
        cartInfo.setAccountId(accountId);
        cartInfo.setCartItems(String.join(",", cartList));
        // process
        cartDao.save(cartInfo);
    }

}
