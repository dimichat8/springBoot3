package com.example.springboot.Controller;

import com.example.springboot.Entity.CustomerInfo;
import com.example.springboot.Service.CustomerInfoService;
import com.example.springboot.Service.CustomerService;
import com.example.springboot.Entity.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerInfoService customerInfoService;

    @GetMapping("/layout")
    public String layout() {
        return "/Customer/layout";
    }

    @GetMapping("/index")
    public String indexCustomer() {
        return "/Customer/index";
    }


    //Display all customer
    @GetMapping("/customertable")
    public String listOfCustomers(Model model) {
        model.addAttribute("customers", customerService.getAllCustomers());
        return "Customer/table";
    }

    ////////
    @GetMapping("/addcustomer")
    public String createCustomerForm(Model model) {

        //Crate model attribute to bind from data
        Customer customer = new Customer();
        List<CustomerInfo> customerInfoList = customerInfoService.getAllCustomerInfo();
        model.addAttribute("customer", customer);
        /*model.addAttribute("customerinfo", customerInfoList);*/
        return "Customer/addcustomer";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer customer) {

        //Save customer to database
        customerService.saveCustomer(customer);
        return "redirect:/customertable";
    }

    @GetMapping("/updateCustomerForm/{customer_id}")
    public String updateCustomerForm(@PathVariable(value = "customer_id") Long customer_id, Model model) {

        //Get customer from the service
        Customer customer = customerService.getCustomerById(customer_id);

        //Set customer as a model attribute to pre-populate the form
        model.addAttribute("customer", customer);
        return "Customer/updatecustomer";
    }

    @PostMapping("/updateCustomer/{customer_id}")
    public String updateCustomer(@PathVariable(value = "customer_id") Long customer_id,
                                 @ModelAttribute("customer") Customer customer) {
        Customer existingCustomer = customerService.getCustomerById(customer_id);
        existingCustomer.setFirstName(customer.getFirstName());
        existingCustomer.setLastName(customer.getLastName());
        existingCustomer.setEmail(customer.getEmail());
        existingCustomer.setCity(customer.getCity());
        existingCustomer.setAddress(customer.getAddress());
        existingCustomer.setPhone(customer.getPhone());
        existingCustomer.setBirthday(customer.getBirthday());
        existingCustomer.setGender(customer.getGender());
        customerService.updateCustomer(existingCustomer);
        return "redirect:/customertable";
    }

    @GetMapping("/deleteCustomer/{customer_id}")
    public String deleteCustomerById(@PathVariable(value = "customer_id") Long customer_id) {

        //Call delete customer method
        customerService.deleteCustomerById(customer_id);
        return "redirect:/customertable";
    }

}