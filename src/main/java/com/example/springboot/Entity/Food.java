package com.example.springboot.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

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

    @NotBlank(message = "Name of food can not be empty")
    @Column(name = "name")
    private String name;
    @Column(name = "grams")
    private Float grams = 100F;
    @Column(name = "calcium")
    private Float calcium;
    @Column(name = "magnesium")
    private Float magnesium;
    @Column(name = "phosphorus")
    private Float phosphorus;

    @ManyToMany(mappedBy = "foods")
    private List<Meal> meals = new ArrayList<>();

    @NotNull(message = "Calories cannot be null")
    @Min(value = 0, message = "Calories must be a positive number")
    @Column(name = "calories")
    private Float calories;


    public Food(Long food_id, Float grams, String name, Float calcium, Float magnesium, Float phosphorus, Float calories, List<Meal> meals) {
        this.food_id = food_id;
        this.name = name;
        this.grams = grams;
        this.calcium = calcium;
        this.magnesium = magnesium;
        this.phosphorus = phosphorus;
        this.calories = calories;
        this.meals = meals;
    }

    public Food() {

    }

    public Food(Float calories) {
        this.calories = calories;
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

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    public Float getCalories() {
        return calories;
    }

    public void setCalories(Float calories) {
        this.calories = calories;
    }



}




