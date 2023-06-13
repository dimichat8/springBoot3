package com.example.springboot.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "food")
public class Food {

    @Id
    @SequenceGenerator(name = "food_id_sequence", sequenceName = "food_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "food_id_sequence")
    @Column(name = "food_id")
    private Long food_id;

    @Column(name = "name")
    private String name;
    @Column(name = "grams")
    private Float grams;
    @Column(name = "calcium")
    private Float calcium;
    @Column(name = "magnesium")
    private Float magnesium;
    @Column(name = "phosphorus")
    private Float phosphorus;

    @ManyToOne
    @JoinColumn(name = "meal_id")
    private Meal meal;


    @Column(name = "calories")
    private Float calories;


    public Food(Long food_id, Float grams, String name, Float calcium, Float magnesium, Float phosphorus, Float calories) {
        this.food_id = food_id;
        this.name = name;
        this.grams = grams;
        this.calcium = calcium;
        this.magnesium = magnesium;
        this.phosphorus = phosphorus;
        this.calories = calories;
    }

    public Food() {

    }

    public Long getFood_id() {
        return food_id;
    }

    public void setFood_id(Long food_id) {
        this.food_id = food_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getGrams() {
        return grams;
    }
    public void setGrams(Float grams) {
        this.grams = grams;
    }

    public Float getCalcium() {
        return calcium;
    }

    public void setCalcium(Float calcium) {
        this.calcium = calcium;
    }

    public Float getMagnesium() {
        return magnesium;
    }

    public void setMagnesium(Float magnesium) {
        this.magnesium = magnesium;
    }

    public Float getPhosphorus() {
        return phosphorus;
    }

    public void setPhosphorus(Float phosphorus) {
        this.phosphorus = phosphorus;
    }

    public Meal getMealPlan() {
        return meal;
    }

    public void setMealPlan(Meal meal) {
        this.meal = meal;
    }

    public Float getCalories() {
        return calories;
    }

    public void setCalories(Float calories) {
        this.calories = calories;
    }



}




