package com.example.springboot.Service.Impl;

import com.example.springboot.Entity.Customer;
import com.example.springboot.Repository.CustomerRepository;
import com.example.springboot.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    public CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer getCustomerById(Long customer_id) {
        Optional<Customer> optional = customerRepository.findById(customer_id);
        Customer customer = null;
        if (optional.isPresent()) {
            customer = optional.get();
        } else {
            throw new RuntimeException("Customer not Found with id : " + customer_id );
        }
        return customer;
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
    @Override
    public void deleteCustomerById(Long customer_id) {
        customerRepository.deleteById(customer_id);
    }
}