package com.example.springboot.Service.Impl;

import com.example.springboot.Entity.MealPlan;
import com.example.springboot.Repository.CustomerRepository;
import com.example.springboot.Repository.FoodRepository;
import com.example.springboot.Repository.MealPlanRepository;
import com.example.springboot.Service.MealPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MealPlanServiceImpl implements MealPlanService {

    @Autowired
    private MealPlanRepository mealPlanRepository;
    @Autowired
    private FoodRepository foodRepository;

    public MealPlanServiceImpl(MealPlanRepository mealPlanRepository, FoodRepository foodRepository) {
        this.mealPlanRepository = mealPlanRepository;
        this.foodRepository = foodRepository;
    }


    @Override
    public List<MealPlan> getAllMealPlans() {
        return mealPlanRepository.findAll();
    }

    @Override
    public MealPlan getMealPlanById(Long mealPlanId) {
        Optional<MealPlan> optional = mealPlanRepository.findById(mealPlanId);
        MealPlan mealPlan = null;
        if (optional.isPresent()){
            mealPlan = optional.get();
        }else {
            throw new RuntimeException("Meal Plan not found with id: " + mealPlanId);
        }
        return mealPlan;
    }

    @Override
    public MealPlan save(MealPlan mealPlan) {
        return mealPlanRepository.save(mealPlan);
    }

    @Override
    public MealPlan update(MealPlan mealPlan) {
        return mealPlanRepository.save(mealPlan);
    }

    @Override
    public void deleteMealPlan(Long mealPlanId) {
        mealPlanRepository.deleteById(mealPlanId);

    }
}
