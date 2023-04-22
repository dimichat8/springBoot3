package com.example.springboot.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "foods")
public class Meal {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meal_id")
    private Long meal_id;

    @Column(name ="customerName")
    private String customerName;
    @Column(name ="mealname")
    private String mealName;
    @Column(name ="calories")
    private float calories;
    @Column(name ="totalFat")
    private Float totalFat;
    @Column(name ="cholesterol")
    private Float cholesterol;
    @Column(name ="protein")
    private Float protein;
    @OneToMany(mappedBy = "meal", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private List<Food> foods = new ArrayList<>();

    public Meal(Long meal_id, String customerName, String name, float calories, Float totalFat, Float cholesterol, Float protein, List<Food> foods) {
        this.meal_id = meal_id;
        this.customerName = customerName;
        this.mealName = name;
        this.calories = calories;
        this.totalFat = totalFat;
        this.cholesterol = cholesterol;
        this.protein = protein;
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String name) {
        this.mealName = name;
    }

    public float getCalories() {
        return calories;
    }

    public void setCalories(float calories) {
        this.calories = calories;
    }

    public Float getTotalFat() {
        return totalFat;
    }

    public void setTotalFat(Float totalFat) {
        this.totalFat = totalFat;
    }

    public Float getCholesterol() {
        return cholesterol;
    }

    public void setCholesterol(Float cholesterol) {
        this.cholesterol = cholesterol;
    }

    public Float getProtein() {
        return protein;
    }

    public void setProtein(Float protein) {
        this.protein = protein;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }
}
