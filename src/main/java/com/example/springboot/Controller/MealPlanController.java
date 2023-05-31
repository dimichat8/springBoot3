package com.example.springboot.Controller;

import com.example.springboot.Entity.Food;
import com.example.springboot.Entity.Meal;
import com.example.springboot.Entity.MealPlan;
import com.example.springboot.Repository.MealPlanRepository;
import com.example.springboot.Service.FoodService;
import com.example.springboot.Service.MealPlanService;
import com.example.springboot.Service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class MealPlanController {

    @Autowired
    private MealPlanService mealPlanService;
    @Autowired
    private MealPlanRepository mealPlanRepository;
    @Autowired
    private FoodService foodService;
    @Autowired
    private MealService mealService;



    @GetMapping("/mealPlan")
    public String mealPlan(Model model) {
        Meal meal = new Meal();
        List<MealPlan> mealPlans = mealPlanService.getAllMealPlans();
        List<Food> foods = foodService.getAllFoods();
        /*meal.setMealPlans(mealPlans);*/
        model.addAttribute("foods", foods);
        model.addAttribute("mealPlans", mealPlans);
        return "/Meal/mealPlan";
    }
    @GetMapping("/mealtable")
    public String listOfMeals(Model model){
        List<Meal> listOfMeals = mealService.getAllMeals();
        model.addAttribute("meals", listOfMeals);
        return  "Meal/table";
    }

  /*  @GetMapping("/createMealPlan")
    public String createMealPlan(Model model) {
        MealPlan mealPlan = new MealPlan();
        mealPlanService.save(mealPlan);
        return "/Meal/table";
    }*/

    @PostMapping
    public String createMealPlan(@RequestParam String dayOfWeek,
                                   @RequestParam("breakfast") List<String> breakfast,
                                   @RequestParam("lunch") List<String> lunch,
                                   @RequestParam("dinner") List<String> dinner) {
        MealPlan mealPlan = new MealPlan();
        mealPlan.setDayOfWeek(dayOfWeek);
        mealPlan.setBreakfast(breakfast);
        mealPlan.setLunch(lunch);
        mealPlan.setDinner(dinner);
        mealPlanService.save(mealPlan);
        return "redirect:/mealtable";
    }
}
