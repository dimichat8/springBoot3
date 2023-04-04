package com.example.springboot.Service;

import com.example.springboot.Entity.Customer;


import java.util.List;

public interface CustomerService {

    List<Customer> getAllCustomers();

    Customer saveCustomer(Customer customer);

    Customer getCustomerById(Long customer_id);


    Customer updateCustomer(Customer customer);

    void deleteCustomerById(Long customer_id);

}
