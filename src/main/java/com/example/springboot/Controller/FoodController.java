package com.example.springboot.Controller;

import com.example.springboot.Entity.Food;
import com.example.springboot.Service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FoodController {

    @Autowired
    public FoodService foodService;

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

    @GetMapping("/deleteFood/{food_id}")
    public String deleteFood(@PathVariable(value = "food_id")Long food_id){

        //Call delete food method
        foodService.deleteFoodById(food_id);
        return "redirect:/foodtable";
    }
}
