package com.fsd.emart.mart.query.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.fsd.emart.common.bean.GoodInfo;
import com.fsd.emart.common.dao.CategoryDao;
import com.fsd.emart.common.dao.ItemDao;
import com.fsd.emart.common.dao.ManufacturerDao;
import com.fsd.emart.common.entity.CategoryData;
import com.fsd.emart.common.entity.ItemInfo;
import com.fsd.emart.common.entity.ManufacturerData;
import com.fsd.emart.common.util.StringUtil;
import com.fsd.emart.mart.query.bean.FilterConditions;
import com.fsd.emart.mart.query.service.MartQueryService;

@Service
public class MartQueryServiceImpl implements MartQueryService {

    @Resource
    private CategoryDao categoryDao;

    @Resource
    private ManufacturerDao manufacturerDao;

    @Resource
    private ItemDao itemDao;

    @Override
    public List<String> getCategoryList() {
        List<CategoryData> list = categoryDao.findAll(Sort.by("id").ascending());

        List<String> result = new ArrayList<>(list.size());
        for (CategoryData categoryData : list) {
            result.add(categoryData.getName());
        }

        return result;
    }

    @Override
    public List<String> getManufacturerList() {
        List<ManufacturerData> list = manufacturerDao.findAll(Sort.by("id").ascending());

        List<String> result = new ArrayList<>(list.size());
        for (ManufacturerData manufacturerData : list) {
            result.add(manufacturerData.getName());
        }

        return result;
    }

    @Override
    public GoodInfo queryItemDetail(String itemId) {
        ItemInfo item = itemDao.getOne(itemId);

        return GoodInfo.getInfoFromEntity(item);
    }

    @Override
    public List<GoodInfo> queryItems(FilterConditions filter, int startRow) {
        List<GoodInfo> list = new ArrayList<>();

        int endRow = startRow + 50;

        List<ItemInfo> queryList = itemDao.findAll(new Specification<ItemInfo>() {

            private static final long serialVersionUID = 292296182108571347L;

            @Override
            public Predicate toPredicate(Root<ItemInfo> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                query.orderBy(criteriaBuilder.desc(root.get("updateTime")));

                if (filter != null) {
                    Predicate cond = criteriaBuilder.like(root.get("name"),
                        "%" + StringUtil.emptyToBlank(filter.getKeyword()) + "%");

                    if (!StringUtil.isEmpty(filter.getCategory())) {
                        cond = criteriaBuilder.and(cond,
                            criteriaBuilder.like(root.get("category"), "%" + filter.getCategory() + "%"));
                    }

                    if (!StringUtil.isEmpty(filter.getManufacturer())) {
                        cond = criteriaBuilder.and(cond,
                            criteriaBuilder.equal(root.get("manufacturer"), filter.getManufacturer()));
                    }

                    if (filter.getPriceF() != null) {
                        cond = criteriaBuilder.and(cond,
                            criteriaBuilder.greaterThanOrEqualTo(root.get("price"), filter.getPriceF()));
                    }

                    if (filter.getPriceT() != null) {
                        cond = criteriaBuilder.and(cond,
                            criteriaBuilder.lessThanOrEqualTo(root.get("price"), filter.getPriceT()));
                    }

                    return cond;
                }
                return null;
            }
        });

        if (startRow < queryList.size()) {
            endRow = Math.min(endRow, queryList.size());

            for (; startRow < endRow; startRow++) {
                list.add(GoodInfo.getInfoFromEntity(queryList.get(startRow)));
            }
        }

        return list;
    }

}
