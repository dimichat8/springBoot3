package com.example.springboot.Controller;

import com.example.springboot.Entity.Meal;
import com.example.springboot.Repository.MealRepository;
import com.example.springboot.Service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MealController {


    @Autowired
    MealRepository mealRepository;
    @Autowired
    MealService mealService;


    //Display all meal

    @GetMapping("/mealtable")
    public String listOfMeals(Model model){
        List<Meal> listOfMeals = mealService.getAllMeals();
        model.addAttribute("meals", listOfMeals);
        return  "Meal/table";
    }

  @GetMapping("/addmeal")
    public String createMealForm(Model model) {

        //Crate model attribute to bind from data
        Meal meal = new Meal();
        model.addAttribute("meal", meal);
        return "/Meal/addmeal";
    }

    @PostMapping("/saveMeal")
    public String saveMeal(@ModelAttribute("meal")Meal meal) {

        //Save meal to database
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
        existingMeal.setCustomerName(meal.getCustomerName());
        existingMeal.setMealName(meal.getMealName());
        existingMeal.setCalories(meal.getCalories());
        existingMeal.setTotalFat(meal.getTotalFat());
        existingMeal.setCholesterol(meal.getCholesterol());
        existingMeal.setProtein(meal.getProtein());
        existingMeal.setFoods(meal.getFoods());
        mealService.updateMeal(existingMeal);
        return "redirect:/mealtable";
    }

    @GetMapping("/deleteMeal/{meal_id}")
    public String deleteMeal(@PathVariable(value = "meal_id")Long meal_id){

        //Call delete meal method
        mealService.deleteMealById(meal_id);
        return "redirect:/mealtable";
    }

}


