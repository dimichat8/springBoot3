/*
package com.example.springboot.Controller;


import com.example.springboot.Entity.Recipe;
import com.example.springboot.Repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class RecipeController {


    @Autowired
    RecipeRepository recipeRepository;


    //Display all food

    @GetMapping("/foodtable")
    public List<Recipe> listOfFoods(){
        return  recipeRepository.findAll();
    }

  */
/*  @GetMapping("/addfood")
    public String createFoodForm(Model model) {

        //Crate model attribute to bind from data
        Recipe recipe = new Recipe();
        model.addAttribute("food", food);
        return "/Food/addfood";
    }

    @PostMapping("/saveFood")
    public String saveFood(@ModelAttribute("food")Food food) {

        //Save food to database
        recipeService.saveFood(food);
        return "redirect:/foodtable";
    }

    @GetMapping("/updateFoodForm/{food_id}")
    public String updateFoodForm(@PathVariable(value = "food_id")Long food_id, Model model) {

        //Get food from the service
        Food food = recipeService.getFoodById(food_id);

        //Set food as a model attribute to pre-populate the form
        model.addAttribute("food", food);
        return "/Food/updatefood";
    }

    @GetMapping("/deleteFood/{food_id}")
    public String deleteFood(@PathVariable(value = "food_id")Long food_id){

        //Call delete food method
        recipeService.deleteFoodById(food_id);
        return "redirect:/foodtable";
    }*//*

}


*/
