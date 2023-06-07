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

    @Column(name ="mealName")
    private String mealName;

    @OneToMany(mappedBy = "meal", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<MealPlan> mealPlans = new ArrayList<>();

    @OneToMany(mappedBy = "meal", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Customer> customers = new ArrayList<>();

    public Meal(Long meal_id, String name, List<MealPlan> mealPlans) {
        this.meal_id = meal_id;
        this.mealName = name;
        this.mealPlans = mealPlans;
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

    public List<MealPlan> getMealPlans() {
        return mealPlans;
    }

    public void setMealPlans(List<MealPlan> mealPlans) {
        this.mealPlans = mealPlans;
    }
}
