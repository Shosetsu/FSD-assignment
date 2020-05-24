package com.fsd.emart.account.bean;

import com.fsd.emart.common.entity.CustomerInfo;

import lombok.Data;

@Data
public class CustomerDetail {
    private String accountType;
    private String accountId;
    private String email;
    private String telNumber;
    private String coName;
    private String postalAddr;
    private String gstin;
    private String bankDetail;

    public static CustomerDetail getInfoFromEntity(CustomerInfo accountDetail) {
        CustomerDetail detail = new CustomerDetail();

        detail.setAccountId(accountDetail.getId());
        detail.setAccountType(accountDetail.getType());
        detail.setEmail(accountDetail.getEmail());
        detail.setTelNumber(accountDetail.getTel());
        detail.setCoName(accountDetail.getCompany());
        detail.setPostalAddr(accountDetail.getAddress());
        detail.setGstin(accountDetail.getGstin());
        detail.setBankDetail(accountDetail.getBankDetail());
        return detail;
    }
}
