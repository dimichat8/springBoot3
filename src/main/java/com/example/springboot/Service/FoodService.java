package com.example.springboot.Service;

import com.example.springboot.Entity.Food;

import java.util.List;

public interface FoodService {

    List<Food> getAllFoods();

    Food saveFood(Food food);

    Food getFoodById(Long food_id);

    Food updateFood(Food food);

    void deleteFoodById(Long food_id);


}
