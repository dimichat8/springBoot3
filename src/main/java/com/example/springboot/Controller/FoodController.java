package com.example.springboot.Controller;

import com.example.springboot.Entity.Food;
import com.example.springboot.Entity.Meal;
import com.example.springboot.Repository.FoodRepository;
import com.example.springboot.Repository.MealRepository;
import com.example.springboot.Service.FoodService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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


    //Display all food

    @GetMapping("/foodtable")
    public String listOfFoods(Model model){
        model.addAttribute("foods", foodService.getAllFoods());
        model.addAttribute("meal", new Meal());
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
    public String saveFood(@Valid @ModelAttribute("food")Food food, BindingResult bindingResult, Model model) {
        boolean thereAreErrors = bindingResult.hasErrors();
        if(thereAreErrors) {
            model.addAttribute("food", food);
            return "Food/addfood";
        }
        foodService.saveFood(food);
        return "redirect:/foodtable";
    }

    @GetMapping("/updateFoodForm/{food_id}")
    public String updateFoodForm(@PathVariable(value = "food_id")Long food_id, Model model) {
        Food food = foodService.getFoodById(food_id);
        model.addAttribute("food", food);
        return "/Food/updatefood";
    }

    @PostMapping("/updateFood/{food_id}")
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

    @PostMapping("/deleteFood/{food_id}")
    public String deleteFoods(@PathVariable(value = "food_id") Long food_id) {
        // Retrieve the Food entity from the database
        Food food = foodRepository.findById(food_id).orElse(null);

        if (food != null) {
            // Remove the Food entity from all associated meals
            for (Meal meal : food.getMeals()) {
                meal.getFoods().remove(food);
            }
            // Clear the meals list in the Food entity
            food.getMeals().clear();
            // Save the Food entity to remove it from associated meals
            foodRepository.save(food);

            // Now, you can safely delete the Food entity
            foodRepository.delete(food);
        }
        return "redirect:/foodtable";
    }

}
