package com.example.springboot.Service;

import com.example.springboot.Entity.Food;
import com.example.springboot.Entity.Meal;

import java.util.List;
import java.util.Optional;

public interface MealService {

    List<Meal> getAllMeals();

    List<Meal> getMealByCustomerId(Long customerId);

    Meal saveMeal(Meal recipe);

    Meal getMealById(Long recipe_id);

    Meal updateMeal(Meal meal);

    void deleteMealById(Long recipe_id);


    List<String> generateCombinations(String[] mealNames);
}
