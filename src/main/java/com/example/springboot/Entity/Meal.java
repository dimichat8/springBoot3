package com.example.springboot.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Meal")
public class Meal {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meal_id")
    private Long meal_id;

    @Column(name ="mealname")
    private String mealName;

    @OneToMany(mappedBy = "meal", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private List<Food> foods = new ArrayList<>();

    public Meal(Long meal_id, String name, List<Food> foods) {
        this.meal_id = meal_id;
        this.mealName = name;
        this.foods = foods;
    }

    public Meal() {

    }

    public void setMeal_id(Long mealId) {
        this.meal_id = meal_id;
    }

    public Long getMeal_id() {
        return meal_id;
    }

    public String getMealName() {
        return mealName;
    }
    public void setMealName(String name) {
        this.mealName = name;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }
}
