package com.example.springboot.Controller;

import com.example.springboot.Entity.Food;
import com.example.springboot.Entity.Meal;
import com.example.springboot.Repository.FoodRepository;
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
    public FoodService foodService;
    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private MealRepository mealRepository;

    /*@GetMapping("/index")
    public String indexFood(){
        return "/Food/index";
    }*/


    //Display all food

    @GetMapping("/foodtable")
    public String listOfFoods(Model model){
        model.addAttribute("foods", foodService.getAllFoods());
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
        existfood.setSodium(food.getSodium());
        existfood.setPotassium(food.getPotassium());
        existfood.setCalcium(food.getCalcium());
        existfood.setMagnesium(food.getMagnesium());
        existfood.setPhosphorus(food.getPhosphorus());
        existfood.setCalories(food.getCalories());
        existfood.setMeal(food.getMeal());
        foodService.updateFood(existfood);

        return "redirect:/foodtable";
    }



    @GetMapping("/deleteFood/{food_id}")
    public String deleteFood(@PathVariable(value = "food_id")Long food_id){

        //Call delete food method
        foodService.deleteFoodById(food_id);
        return "redirect:/foodtable";
    }

    @PostMapping("/mealtable")
    public String selectedFoodsForMeal(@RequestBody List<Long> foodIds) {
    List<Food> selectedFoodsForMeal = foodRepository.findAllById(foodIds);

    List<Meal> mealRecords =new ArrayList<>();
    for(Food foods : selectedFoodsForMeal) {
        Meal meal = new Meal();
        meal.setMealName(meal.getMealName());
        meal.setFoods(foods.getFoods());
        mealRecords.add(meal);
    }
    mealRepository.saveAll(mealRecords);
        return "/Meal/table";
    }
}
