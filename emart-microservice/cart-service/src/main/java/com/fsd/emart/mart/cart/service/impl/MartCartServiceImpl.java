package com.fsd.emart.mart.cart.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
			GoodInfo goodInfo = new GoodInfo();
			goodInfo.setId(item.getItemId());
			goodInfo.setName(item.getName());
			goodInfo.setCategory(StringUtil.splitString(item.getCategory(), ","));
			goodInfo.setManufacturer(item.getManufacturer());
			goodInfo.setOwner(item.getOwnerId());
			goodInfo.setPrice(item.getPrice());
			goodInfo.setStock(item.getStock());
			goodInfo.setDetail(item.getDetail());
			goodInfo.setCreatedDate(item.getUpdateTime());
			goodInfo.setStatus(item.getStatus());
			result.add(goodInfo);
		}

		return result;
	}

	@Override
	public void updateCartList(String accountId, String[] cartList) {
		CartInfo cartInfo = new CartInfo();
		cartInfo.setAccountId(accountId);
		cartInfo.setCartItems(String.join(",", cartList));
		cartInfo.setUpdateTime(new Timestamp(new Date().getTime()));
		// process
		cartDao.save(cartInfo);
	}

}
