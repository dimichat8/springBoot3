package com.example.springboot.Service.Impl;

import com.example.springboot.Entity.Meal;
import com.example.springboot.Repository.MealRepository;
import com.example.springboot.Service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<Meal> getAllMeals() {
        return mealRepository.findAll();
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
            throw new RuntimeException("Meal not Found with id : " + meal_id );
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
}