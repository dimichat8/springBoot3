package com.example.springboot.Controller;

import com.example.springboot.Entity.Customer;
import com.example.springboot.Entity.Food;
import com.example.springboot.Entity.Meal;
import com.example.springboot.Repository.CustomerRepository;
import com.example.springboot.Repository.FoodRepository;
import com.example.springboot.Repository.MealRepository;
import com.example.springboot.Service.CustomerService;
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
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

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



    //Display all meal

    @GetMapping("/mealtable")
    public String listOfMeals( Model model){
        List<Meal> listOfMeals = mealService.getAllMeals();
        /*List<Meal> breakfast = mealRepository.findBreakfast();*/
        model.addAttribute("meals", listOfMeals);
        /*model.addAttribute("breakfast", breakfast);*/
        return  "Meal/table";
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
        model.addAttribute("customerId", customerId);
        return "/Meal/addmeal";
    }

    @PostMapping("/{customer_id}/saveMeal/foodIds")
    public String saveMeal(@PathVariable(value = "customer_id") Long customerId,
                           @ModelAttribute("dayOfWeek")  String dayOfWeek,
                           @ModelAttribute("breakfast") String breakfast,
                           @ModelAttribute("desert") String desert,
                           @ModelAttribute("lunch") String lunch,
                           @ModelAttribute("snack") String snack,
                           @ModelAttribute("dinner") String dinner,
                           @ModelAttribute("meal") Meal meal) {
        Customer customer = customerRepository.findById(customerId).get();

        meal = new Meal();
        meal.getBreakfast();

        /*meal.setDesert(desert);*/

        //List<Food> foods = foodRepository.findAllById(foodIds);
        //List<Food> foods = foodRepository.findAllById(foodIds);

        meal.setCustomer(customer);
        meal.setDayOfWeek(dayOfWeek);

        customer.getMeal().add(meal);
        //Save meal to database
        //meal.getFoods().add((Food) foods);
        mealService.saveMeal(meal);
        return "redirect:/mealtable";
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

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=diet_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<Meal> mealList = mealService.getAllMeals();

        UserPDFExporter exporter = new UserPDFExporter(mealList);
        exporter.export(response);

    }

}


