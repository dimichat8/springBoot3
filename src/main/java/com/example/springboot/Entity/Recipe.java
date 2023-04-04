/*
package com.example.springboot.Entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "recipes")
public class Recipe {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_id")
    private Long recipe_id;

    @Column(name ="userName")
    private String userName;
    @Column(name ="name")
    private String name;
    @Column(name ="calories")
    private float calories;
    @Column(name ="totalFat")
    private Float totalFat;
    @Column(name ="cholesterol")
    private Float cholesterol;
    @Column(name ="protein")
    private Float protein;
    @OneToMany(mappedBy = "recipe", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @Column(name = "foods")
    private Set<Recipe> foods = new HashSet<Recipe>();

    public Recipe(Long recipe_id, String userName, String name, float calories, Float totalFat, Float cholesterol, Float protein, Set<Recipe> foods) {
        this.recipe_id = recipe_id;
        this.userName = userName;
        this.name = name;
        this.calories = calories;
        this.totalFat = totalFat;
        this.cholesterol = cholesterol;
        this.protein = protein;
        this.foods = foods;
    }

    public void setRecipe_id(Long recipeId) {
        this.recipe_id = recipe_id;
    }

    public Long getRecipe_id() {
        return recipe_id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Set<Recipe> getFoods() {
        return foods;
    }

    public void setFoods(Set<Recipe> foods) {
        this.foods = foods;
    }
}
*/
