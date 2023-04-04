package com.example.springboot.Service.Impl;

import com.example.springboot.Entity.CustomerInfo;
import com.example.springboot.Repository.CustomerInfoRepository;
import com.example.springboot.Service.CustomerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerInfoServiceImpl implements CustomerInfoService {

    @Autowired
    private CustomerInfoRepository customerInfoRepository;

    public CustomerInfoServiceImpl(CustomerInfoRepository customerInfoRepository) {
        this.customerInfoRepository = customerInfoRepository;
    }

    @Override
    public List<CustomerInfo> getAllCustomerInfo() {
        return customerInfoRepository.findAll();
    }

/*@Override
    public CustomerDietaryInfo saveCustomerDietaryInfo(CustomerDietaryInfo customerDietaryInfo) {
        return null;
    }*/


    @Override
    public CustomerInfo saveCustomerInfo(CustomerInfo customerInfo) {
        return customerInfoRepository.save(customerInfo);
    }


    @Override
    public CustomerInfoService getCustomerInfoById(Long customerdietary_id) {
        Optional<CustomerInfo> optional = customerInfoRepository.findById(customerdietary_id);
            CustomerInfo customerInfo = null;
            if (optional.isPresent()){
                customerInfo = (CustomerInfo) optional.get();
            }else {
                throw new RuntimeException("CustomerDietaryInfo not found with id :" + customerdietary_id);
            }
        return (CustomerInfoService) customerInfo;
    }

    @Override
    public CustomerInfo updateCustomerInfo(CustomerInfo customerInfo) {
        return customerInfoRepository.save(customerInfo);
    }

    @Override
    public void deleteCustomerInfoById(Long customerdietary_id) {
         customerInfoRepository.deleteById(customerdietary_id);
    }


}
