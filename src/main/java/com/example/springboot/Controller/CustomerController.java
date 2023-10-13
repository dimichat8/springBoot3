package com.example.springboot.Controller;

import com.example.springboot.Entity.Customer;
import com.example.springboot.Entity.Meal;
import com.example.springboot.Service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @GetMapping("/index")
    public String getCustomerData(Model model) {
        List<Customer> customers = customerService.getAllCustomers();
        model.addAttribute("customer" , customers);
        return "/Customer/index";
    }
    @GetMapping("/customerTable")
    public String listOfCustomers(Model model) {
        List<Customer> customers = customerService.getAllCustomers();
        Meal meal = new Meal();
        model.addAttribute("customers", customers);
        model.addAttribute("meal", meal);
        return "Customer/table";
    }

    @GetMapping("/addCustomer")
    public String createCustomerForm(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "Customer/addCustomer";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@Valid @ModelAttribute("customer") Customer customer, BindingResult bindingResult, Model model) {

        boolean thereAreErrors = bindingResult.hasErrors();
        if(thereAreErrors) {
            model.addAttribute("customer", customer);
            return "Customer/addCustomer";
        }
        //Save customer to database
        customerService.saveCustomer(customer);
        return "redirect:/customerTable";
    }

    @GetMapping("/updateCustomerForm/{customer_id}")
    public String updateCustomerForm(@PathVariable(value = "customer_id") Long customer_id, Model model) {
        Customer customer = customerService.getCustomerById(customer_id);
        model.addAttribute("customer", customer);
        return "Customer/updateCustomer";
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
        return "redirect:/customerTable";
    }

    @PostMapping("/deleteCustomer/{customer_id}")
    public String deleteCustomerById(@PathVariable(value = "customer_id") Long customer_id) {

        //Call delete customer method
        customerService.deleteCustomerById(customer_id);
        return "redirect:/customerTable";
    }
}