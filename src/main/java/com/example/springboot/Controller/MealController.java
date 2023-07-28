package com.example.springboot.Controller;

import com.example.springboot.Entity.Customer;
import com.example.springboot.Entity.Food;
import com.example.springboot.Entity.Meal;
import com.example.springboot.Entity.MealPlan;
import com.example.springboot.Repository.CustomerRepository;
import com.example.springboot.Repository.FoodRepository;
import com.example.springboot.Repository.MealRepository;
import com.example.springboot.Service.CustomerService;
import com.example.springboot.Service.MealPlanService;
import com.example.springboot.Service.MealService;
import com.example.springboot.pdf.UserPDFExporter;
import com.itextpdf.text.DocumentException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class MealController {


    @Autowired
    MealRepository mealRepository;
    @Autowired
    MealService mealService;
    @Autowired
    CustomerService customerService;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private MealPlanService mealPlanService;



    //Display all meal

    @GetMapping("/{customer_id}/mealtable")
    public String listOfMeals(@PathVariable("customer_id") Long customerId, Model model) {
        Customer customer = customerService.getCustomerById(customerId);
        if (customer == null) {
            // Handle the case where the customer with the specified ID is not found.
            // You can return an error page or redirect to another page as needed.
            return "redirect:/error-page";
        }
        Meal meal = new Meal();
        List<Meal> listOfMeals = customer.getMeals();
        model.addAttribute("meals", listOfMeals);
        model.addAttribute("meal", meal);
        model.addAttribute("customer", customer);
        return "Meal/table";
    }

    @GetMapping("/{customer_id}/addmeal")
    public String createMealForm(@PathVariable(value = "customer_id") Customer customerId,
                                 Model model) {
        //Crate model attribute to bind from data
        Meal meal = new Meal();
        List<Food> foods = foodRepository.findAll();
        meal.setCustomer(customerId);
        model.addAttribute("foods", foods);
        model.addAttribute("meal", meal);
        model.addAttribute("customer", customerId);
        return "/Meal/addmeal";
    }


    @PostMapping("/{customer_id}/saveMeal/foodIds")
    public String saveMeal(@PathVariable(value = "customer_id") Long customerId,
                           @RequestParam("dayOfWeek") List<String> daysOfWeek,
                           @ModelAttribute("mealName") String mealName,
                           @ModelAttribute("breakfastId") String breakfastId,
                           @ModelAttribute("breakfast") String breakfast,
                           @ModelAttribute("desertId") String desertId,
                           @ModelAttribute("desert") String desert,
                           @ModelAttribute("lunchId") String lunchId,
                           @ModelAttribute("lunch") String lunch,
                           @ModelAttribute("snackId") String snackId,
                           @ModelAttribute("snack") String snack,
                           @ModelAttribute("dinnerId") String dinnerId,
                           @ModelAttribute("dinner") String dinner) {
        Customer customer = customerRepository.findById(customerId).get();
        System.out.println("Selected Days of the Week:");
        for (String dayOfWeek : daysOfWeek) {
            System.out.println(dayOfWeek);
        }
        for (String dayOfWeek : daysOfWeek) {
            Meal meal = new Meal();
            meal.setCustomer(customer);
            meal.setDayOfWeek(dayOfWeek);
            meal.setMealName(mealName);

            switch (mealName) {
                case "Breakfast":
                    List<String> breakfastList = new ArrayList<>();
                    breakfastList.add(breakfast);
                    meal.setBreakfast(breakfastList);
                    break;
                case "Desert":
                    List<String> desertList = new ArrayList<>();
                    desertList.add(desert);
                    meal.setDesert(desertList);
                    break;
                case "Lunch":
                    List<String> lunchList = new ArrayList<>();
                    lunchList.add(lunch);
                    meal.setLunch(lunchList);
                    break;
                case "Snack":
                    List<String> snackList = new ArrayList<>();
                    snackList.add(snack);
                    meal.setSnack(snackList);
                    break;
                case "Dinner":
                    List<String> dinnerList = new ArrayList<>();
                    dinnerList.add(dinner);
                    meal.setDinner(dinnerList);
                    break;
                default:
                    // Handle the case when the mealName doesn't match any of the above cases
                    // For example, you could throw an exception or log a warning.
                    break;
            }
            MealPlan mealPlan = new MealPlan();
            mealPlan.setCustomer(customer);
            mealPlanService.save(mealPlan);
            mealPlan.getMeals().add(meal);
            meal.setMealPlan(mealPlan);
            customer.getMeal().add(meal);
            mealService.saveMeal(meal);
        }
            return "redirect:/{customer_id}/mealtable";
        }

    @GetMapping("/updateMealForm/{meal_id}")
    public String updateMealForm(@PathVariable(value = "meal_id")Long meal_id, Model model) {

        //Get meal from the service
        Meal meal = mealService.getMealById(meal_id);

        //Set meal as a model attribute to pre-populate the form
        model.addAttribute("meal", meal);
        return "/Meal/updatemeal";
    }

    @PostMapping("/updateMeal/{meal_id}")
    public String updateMeal(@PathVariable(value = "meal_id") Long meal_id,
                                 @ModelAttribute("meal") Meal meal) {
        Meal existingMeal = mealService.getMealById(meal_id);
        existingMeal.setMealName(meal.getMealName());
        mealService.updateMeal(existingMeal);
        return "redirect:/mealtable";
    }

    @GetMapping("/deleteMeal/{meal_id}")
    public String deleteMeal(@PathVariable(value = "meal_id")Long meal_id){

        //Call delete meal method
        mealService.deleteMealById(meal_id);
        return "redirect:/mealtable";
    }

    @GetMapping("/dietProgram/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        Customer customer = new Customer();
        String fullname = customer.getFirstName() + customer.getLastName();
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=diet_for_" + fullname + "_" + currentDateTime +  ".pdf";
        response.setHeader(headerKey, headerValue);
        List<Meal> mealList = mealService.getAllMeals();
        UserPDFExporter exporter = new UserPDFExporter(mealList);
        exporter.export(response);

    }

}


