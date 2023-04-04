package com.example.springboot.Service;

import com.example.springboot.Entity.CustomerInfo;

import java.util.List;

public interface CustomerInfoService {

    List<CustomerInfo> getAllCustomerInfo();

    CustomerInfo saveCustomerInfo(CustomerInfo customerInfo);

    CustomerInfoService getCustomerInfoById (Long customerdietary_id);

    CustomerInfo updateCustomerInfo(CustomerInfo customerInfo);

    void deleteCustomerInfoById(Long customerdietary_id);
}

