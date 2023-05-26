package com.example.springboot.Repository;

import com.example.springboot.Entity.CustomerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerInfoRepository extends JpaRepository<CustomerInfo, Long> {
    @Query(value = "SELECT * FROM Customer_info", nativeQuery = true)
    List<CustomerInfo> readCustomerInfoByCustomer();
}
