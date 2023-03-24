package com.example.springboot.Service.Impl;

import com.example.springboot.Entity.Food;
import com.example.springboot.Repository.FoodRepository;
import com.example.springboot.Service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.List;
import java.util.Optional;

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    public FoodRepository foodRepository;

    public FoodServiceImpl(FoodRepository foodRepository){
        this.foodRepository = foodRepository;
    }

    @Override
    public List<Food> getAllFoods() { return foodRepository.findAll();}

    @Override
    public Food saveFood(Food food) {
        return foodRepository.save(food);
    }

    @Override
    public Food getFoodById(Long food_id) {
        Optional<Food> optional = foodRepository.findById(food_id);
        Food food = null;
        if (optional.isPresent()){
            food = optional.get();
        }else {
            throw new RuntimeException("Food not found with id: " + food_id);
        }
        return food;
    }

    @Override
    public void deleteFoodById(Long food_id) {
         foodRepository.deleteById(food_id);

    }


}
