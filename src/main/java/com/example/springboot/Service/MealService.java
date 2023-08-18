package com.example.springboot.Service;

import com.example.springboot.DTO.MealDataDto;
import com.example.springboot.Entity.Food;
import com.example.springboot.Entity.Meal;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MealService {



    List<Meal> getMealByCustomerId(Long customerId);

    Meal saveMeal(Meal recipe);

    Meal getMealById(Long recipe_id);

    Meal updateMeal(Meal meal);

    void deleteMealById(Long recipe_id);

    List<String> generateCombinations(String[] mealNames);

    List<MealDataDto> getAllMealData();

    List<MealDataDto> allMealDataForEachCustomer(Long customerId);

    List<Meal> allMealDataAccordingToDates(Long customerId, LocalDate dateFrom, LocalDate dateTo);

}
