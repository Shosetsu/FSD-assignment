package com.fsd.emart.account.service.impl;

import java.sql.Timestamp;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fsd.emart.account.bean.AccountDetailUpdateForm;
import com.fsd.emart.account.service.AccountService;
import com.fsd.emart.common.constants.Constants;
import com.fsd.emart.common.dao.AuthDao;
import com.fsd.emart.common.dao.CartDao;
import com.fsd.emart.common.dao.CustomerDao;
import com.fsd.emart.common.dao.ItemDao;
import com.fsd.emart.common.dao.SessionDao;
import com.fsd.emart.common.entity.AuthInfo;
import com.fsd.emart.common.entity.CustomerInfo;
import com.fsd.emart.common.exception.ApplicationException;
import com.fsd.emart.common.exception.SystemException;
import com.fsd.emart.common.util.CryptoUtil;
import com.fsd.emart.common.util.StringUtil;

@Transactional
@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private AuthDao authDao;

    @Resource
    private CustomerDao customerDao;

    @Resource
    private SessionDao sessionDao;

    @Resource
    private CartDao cartDao;

    @Resource
    private ItemDao itemDao;

    @Resource
    private CryptoUtil cryptoUtil;

    @Override
    public void register(CustomerInfo info, String newPassword) {

        // check mail exist
        if (customerDao.findByEmail(info.getEmail()).isPresent()) {
            throw new ApplicationException("The Email address is registered.");
        }

        // check accountId exist
        if (customerDao.findById(info.getId()).isPresent()) {
            throw new ApplicationException("The Account Id is registered.");
        }

        // pre-process
        AuthInfo authInfo = new AuthInfo();
        authInfo.setId(info.getId());
        authInfo.setPassword(cryptoUtil.encodePassword(newPassword));

        // process
        customerDao.save(info);
        authDao.save(authInfo);
    }

    @Override
    public void unregist(String accountId, String password) {

        // check authorization
        Optional<AuthInfo> authInfo = authDao.findById(accountId);

        if (!authInfo.isPresent()) {
            // Not User?
            throw new SystemException("System Error.");
        }

        if (!cryptoUtil.comparePassword(password, authInfo.get().getPassword())) {
            throw new ApplicationException("Password invalid.");
        }

        // process master
        customerDao.deleteById(accountId);
        authDao.deleteById(accountId);
        sessionDao.deleteById(accountId);

        // process part.2
        cartDao.deleteById(accountId);

        itemDao.deleteAll(itemDao.findByOwnerIdOrderByUpdateTimeDesc(accountId));
    }

    @Override
    public void findAccount(String mail) {
        // check mail exist
        Optional<CustomerInfo> customerInfo = customerDao.findByEmail(mail);

        if (!customerInfo.isPresent()) {
            throw new ApplicationException("The Email address is not registered.");
        }

        // process
        String newPassword = cryptoUtil.createRandomPassword();

        AuthInfo authInfo = new AuthInfo();
        authInfo.setId(customerInfo.get().getId());
        authInfo.setPassword(cryptoUtil.encodePassword(newPassword));

        // TODO Call mail server
    }

    @Override
    public Timestamp getSellerCreateTime(String accountId) {
        return getAccountDetail(accountId).getSellerDate();
    }

    @Override
    public CustomerInfo getAccountDetail(String accountId) {

        Optional<CustomerInfo> customerInfo = customerDao.findById(accountId);

        // check accountId exist
        if (!customerInfo.isPresent()) {
            throw new SystemException("System Error.");
        }

        return customerInfo.get();
    }

    @Override
    public void updateAccountDetail(AccountDetailUpdateForm form, String targetId) {

        CustomerInfo currentInfo = customerDao.findById(targetId).orElse(null);

        // check accountId exist
        if (currentInfo == null) {
            throw new SystemException("System Error.");
        }

        // check old password
        if (!cryptoUtil.comparePassword(form.getPassword(), authDao.getOne(targetId).getPassword())) {
            throw new ApplicationException("Password invalid.");
        }

        if (Constants.ROLE_BUYER.equals(currentInfo.getType()) && Constants.ROLE_SELLER.equals(form.getAccountType())) {
            currentInfo.setType(Constants.ROLE_SELLER);
            currentInfo.setSellerDate(new Timestamp(System.currentTimeMillis()));
        }

        currentInfo.setEmail(StringUtil.getNonblankString(form.getEmail()));

        if (Constants.ROLE_SELLER.equals(currentInfo.getType())) {
            currentInfo.setCompany(StringUtil.getNonblankString(form.getCoName()));
            currentInfo.setAddress(StringUtil.getNonblankString(form.getPostalAddr()));
            currentInfo.setGstin(StringUtil.emptyToString(form.getGSTIN(), currentInfo.getGstin()));
            currentInfo.setBankDetail(StringUtil.emptyToString(form.getBankDetail(), currentInfo.getBankDetail()));
            currentInfo.setTel(form.getTelNumber());
        } else {
            currentInfo.setTel(StringUtil.getNonblankString(form.getTelNumber()));
        }
        // update account info
        customerDao.saveAndFlush(currentInfo);

        // update password
        if (!StringUtil.isEmpty(form.getNewPassword())) {
            AuthInfo authInfo = new AuthInfo();
            authInfo.setId(currentInfo.getId());
            authInfo.setPassword(cryptoUtil.encodePassword(form.getPassword()));

            authDao.saveAndFlush(authInfo);
        }
    }
}
