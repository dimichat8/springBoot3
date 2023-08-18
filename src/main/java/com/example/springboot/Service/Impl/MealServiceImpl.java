package com.example.springboot.Service.Impl;

import com.example.springboot.DTO.MealDataDto;
import com.example.springboot.Entity.Meal;
import com.example.springboot.Repository.MealRepository;
import com.example.springboot.Service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class MealServiceImpl implements MealService {

    @Autowired
    public MealRepository mealRepository;


    public MealServiceImpl(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }


    @Override
    public List<Meal> getMealByCustomerId(Long customerId) {
        return mealRepository.findMealDataByCustomerId(customerId);
    }

    @Override
    public Meal saveMeal(Meal meal) {
        return mealRepository.save(meal);
    }

    @Override
    public Meal getMealById(Long meal_id) {
        Optional<Meal> optional = mealRepository.findById(meal_id);
        Meal meal = null;
        if (optional.isPresent()) {
            meal = optional.get();
        } else {
            throw new RuntimeException("Meal not Found with id : " + meal_id);
        }
        return meal;
    }

    @Override
    public Meal updateMeal(Meal meal) {
        return mealRepository.save(meal);
    }

    @Override
    public void deleteMealById(Long meal_id) {
        mealRepository.deleteById(meal_id);
    }

    @Override
    public List<String> generateCombinations(String[] mealNames) {
        List<String> combinations = new ArrayList<>();
        int n = mealNames.length;

        for (int i = 1; i < (1 << n); i++) {
            StringBuilder combination = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) > 0) {
                    if (combination.length() > 0) {
                        combination.append(", ");
                    }
                    combination.append(mealNames[j]);
                }
            }
            combinations.add(combination.toString());
        }

        return combinations;
    }

    @Override
    public List<MealDataDto> getAllMealData() {
        return mealRepository.getAllMealData();
    }

    @Override
    public List<MealDataDto> allMealDataForEachCustomer(Long customerId) {
        return mealRepository.getAllMealDataForEachCustomer(customerId);
    }

    @Override
    public List<Meal> allMealDataAccordingToDates(Long customerId, LocalDate dateFrom, LocalDate dateTo) {
        return mealRepository.getAllMealDataAccordingToDates(customerId, dateFrom, dateTo);
    }
}