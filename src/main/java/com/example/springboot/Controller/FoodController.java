package com.example.springboot.Controller;

import com.example.springboot.Entity.Food;
import com.example.springboot.Entity.Meal;
import com.example.springboot.Entity.MealPlan;
import com.example.springboot.Repository.FoodRepository;
import com.example.springboot.Repository.MealPlanRepository;
import com.example.springboot.Repository.MealRepository;
import com.example.springboot.Service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FoodController {

    @Autowired
    private FoodService foodService;
    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private MealRepository mealRepository;
    @Autowired
    private MealPlanRepository mealPlanRepository;

    //Display all food

    @GetMapping("/foodtable")
    public String listOfFoods(Model model){
        model.addAttribute("foods", foodService.getAllFoods());
        model.addAttribute("mealPlan", new MealPlan());
        return "/Food/table";
    }

    @GetMapping("/addfood")
    public String createFoodForm(Model model) {

        //Crate model attribute to bind from data
        Food food = new Food();
        model.addAttribute("food", food);
        return "/Food/addfood";
    }

    @PostMapping("/saveFood")
    public String saveFood(@ModelAttribute("food")Food food) {

        //Save food to database
        foodService.saveFood(food);
        return "redirect:/foodtable";
    }

    @GetMapping("/updateFoodForm/{food_id}")
    public String updateFoodForm(@PathVariable(value = "food_id")Long food_id, Model model) {

        //Get food from the service
        Food food = foodService.getFoodById(food_id);

        //Set food as a model attribute to pre-populate the form
        model.addAttribute("food", food);
        return "/Food/updatefood";
    }

    @GetMapping("/updateFood")
    public String updateFood(@PathVariable(value = "food_id") Long foodId,
                             @ModelAttribute("food") Food food) {
        Food existfood = foodService.getFoodById(foodId);
        existfood.setName(food.getName());
        existfood.setGrams(food.getGrams());
        existfood.setCalcium(food.getCalcium());
        existfood.setMagnesium(food.getMagnesium());
        existfood.setPhosphorus(food.getPhosphorus());
        existfood.setCalories(food.getCalories());
        foodService.updateFood(existfood);

        return "redirect:/foodtable";
    }



    @GetMapping("/deleteFood/{food_id}")
    public String deleteFood(@PathVariable(value = "food_id")Long food_id){

        //Call delete food method
        foodService.deleteFoodById(food_id);
        return "redirect:/foodtable";
    }

    @PostMapping("/foods/add-to-meal")
    public String addToMeal(@PathVariable("foodIds") List<Long> foodIds ,@ModelAttribute("mealPlan") MealPlan mealPlan) {
        // Retrieve selected food IDs from the form
        List<Food> selectedFoodIds = foodRepository.findAllById(foodIds);

        // Add the selected foods to the meal table (you can customize this logic)
        for (Food foodId : selectedFoodIds) {
            Food food = foodRepository.findById(foodId.getFood_id()).orElse(null);
            if (food != null) {
                // Create a new meal and add the selected food to it
                MealPlan mealPlan1 = new MealPlan();
                /*mealPlan.setBreakfast(s);
                mealPlan.setDessert();
                mealPlan.setLunch();
                mealPlan.setSnack();
                mealPlan.setDinner();*/
                mealPlan1.setFoods(selectedFoodIds);
                mealPlanRepository.save(mealPlan);
            }
        }

        return "redirect:/mealtable";
    }
}
