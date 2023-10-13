package com.example.springboot.Controller;

import com.example.springboot.Entity.Food;
import com.example.springboot.Entity.Meal;
import com.example.springboot.Repository.FoodRepository;
import com.example.springboot.Service.FoodService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FoodController {

    @Autowired
    private FoodService foodService;
    @Autowired
    private FoodRepository foodRepository;


    @GetMapping("/getCalories/{foodId}")
    public ResponseEntity<Food> getCalories( @PathVariable Long foodId) {
        Food food = foodRepository.findById(foodId).orElse(null);

        if (food != null) {
            Food response = new Food(food.getCalories());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/foodtable")
    public String listOfFoods(Model model){
        model.addAttribute("foods", foodService.getAllFoods());
        model.addAttribute("meal", new Meal());
        return "/Food/table";
    }

    @GetMapping("/addfood")
    public String createFoodForm(Model model) {


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

        Food food = foodRepository.findById(food_id).orElse(null);

        if (food != null) {
            for (Meal meal : food.getMeals()) {
                meal.getFoods().remove(food);
            }
            food.getMeals().clear();

            foodRepository.save(food);

            foodRepository.delete(food);
        }
        return "redirect:/foodtable";
    }

}
