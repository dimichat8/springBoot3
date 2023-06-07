package com.example.springboot.Controller;

import com.example.springboot.Entity.CustomerInfo;
import com.example.springboot.Entity.Food;
import com.example.springboot.Entity.Meal;
import com.example.springboot.Service.CustomerInfoService;
import com.example.springboot.Service.CustomerService;
import com.example.springboot.Entity.Customer;

import com.example.springboot.Service.FoodService;
import com.example.springboot.Service.MealService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerInfoService customerInfoService;
    @Autowired
    private FoodService foodService;

    @GetMapping("/layout")
    public String layout() {
        return "/Customer/layout";
    }

    @GetMapping("/index")
    public String indexCustomer() {
        return "/Customer/index";
    }
    @GetMapping("/customertable")
    public String listOfCustomers(Model model) {
        model.addAttribute("customers", customerService.getAllCustomers());
        return "Customer/table";
    }

    @GetMapping("/addcustomer")
    public String createCustomerForm(Model model) {

        Customer customer = new Customer();
        List<CustomerInfo> customerInfoList = customerInfoService.getAllCustomerInfo();
        model.addAttribute("customer", customer);
        return "Customer/addcustomer";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@Valid @ModelAttribute("customer") Customer customer, BindingResult bindingResult, Model model) {

        boolean thereAreErrors = bindingResult.hasErrors();
        if(thereAreErrors) {
            model.addAttribute("customer", customer);
            return "Customer/addcustomer";
        }
        //Save customer to database
        customerService.saveCustomer(customer);
        return "redirect:/customertable";
    }

    @GetMapping("/updateCustomerForm/{customer_id}")
    public String updateCustomerForm(@PathVariable(value = "customer_id") Long customer_id, Model model) {
        Customer customer = customerService.getCustomerById(customer_id);
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