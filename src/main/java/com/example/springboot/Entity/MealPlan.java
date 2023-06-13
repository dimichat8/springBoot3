/*
package com.example.springboot.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "MealPlan")
public class MealPlan {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mealPlanId")
    private Long mealPlanId;

    @Column(name = "dayOfWeek")
    private String dayOfWeek;

    @ElementCollection
    @CollectionTable(name = "Breakfast", joinColumns = @JoinColumn(name = "mealPlanId"))
    @Column(name = "breakfast")
    private List<String> breakfast;

    @ElementCollection
    @CollectionTable(name = "Desert", joinColumns = @JoinColumn(name = "mealPlanId"))
    @Column(name = "desert")
    private List<String> desert;

    @ElementCollection
    @CollectionTable(name = "Lunch", joinColumns = @JoinColumn(name = "mealPlanId"))
    @Column(name = "lunch")
    private List<String> lunch;

    @ElementCollection
    @CollectionTable(name = "Snack", joinColumns = @JoinColumn(name = "mealPlanId"))
    @Column(name = "snack")
    private List<String> snack;
    @ElementCollection
    @CollectionTable(name = "Dinner", joinColumns = @JoinColumn(name = "mealPlanId"))
    @Column(name = "dinner")
    private List<String> dinner;

    @OneToMany(mappedBy = "mealPlan")
    private List<Food> foods = new ArrayList<>();
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "mealId")
    private Meal meal = new Meal();


    public MealPlan() {
    }

    public MealPlan(Long mealPlanId, String dayOfWeek, List<String> breakfast, List<String> desert, List<String> lunch, List<String> snack, List<String> dinner) {
        this.mealPlanId = mealPlanId;
        this.dayOfWeek = dayOfWeek;
        this.breakfast = breakfast;
        this.desert = desert;
        this.lunch = lunch;
        this.snack = snack;
        this.dinner = dinner;
    }

    public Long getMealPlanId() {
        return mealPlanId;
    }

    public void setMealPlanId(Long mealPlanId) {
        this.mealPlanId = mealPlanId;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public List<String> getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(List<String> breakfast) {
        this.breakfast = breakfast;
    }

    public List<String> getDessert() {
        return desert;
    }

    public void setDessert(List<String> dessert) {
        this.desert = dessert;
    }

    public List<String> getLunch() {
        return lunch;
    }

    public void setLunch(List<String> lunch) {
        this.lunch = lunch;
    }

    public List<String> getSnack() {
        return snack;
    }

    public void setSnack(List<String> snack) {
        this.snack = snack;
    }

    public List<String> getDinner() {
        return dinner;
    }

    public void setDinner(List<String> dinner) {
        this.dinner = dinner;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }
}
*/
