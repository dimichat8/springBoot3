package com.example.springboot.Entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Embeddable
@Table(name = "Meal")
public class Meal {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meal_id")
    private Long meal_id;

    @Column(name ="mealName")
    private String mealName;

    @Column(name = "dayOfWeek")
    private String dayOfWeek;

    @ElementCollection
    @CollectionTable(name = "Breakfast", joinColumns = @JoinColumn(name = "mealId"))
    @Column(name = "breakfast")
    private List<String> breakfast;

    @ElementCollection
    @CollectionTable(name = "Desert", joinColumns = @JoinColumn(name = "mealId"))
    @Column(name = "desert")
    private List<String> desert;

    @ElementCollection
    @CollectionTable(name = "Lunch", joinColumns = @JoinColumn(name = "mealId"))
    @Column(name = "lunch")
    private List<String> lunch;

    @ElementCollection
    @CollectionTable(name = "Snack", joinColumns = @JoinColumn(name = "mealId"))
    @Column(name = "snack")
    private List<String> snack;
    @ElementCollection
    @CollectionTable(name = "Dinner", joinColumns = @JoinColumn(name = "mealId"))
    @Column(name = "dinner")
    private List<String> dinner;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "meal_food",
            joinColumns = @JoinColumn(name = "meal_id"),
            inverseJoinColumns = @JoinColumn(name = "food_id"))
    private List<Food> foods = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "mealPlan")
    private MealPlan mealPlan;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateFrom;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateTo;

    @Column(name = "meal_grams")
    private Integer mealGrams;

    public Meal(Long meal_id, String mealName, String dayOfWeek, List<String> breakfast, List<String> desert, List<String> lunch, List<String> snack, List<String> dinner, List<Food> foods, Customer customer, MealPlan mealPlan, LocalDate dateFrom, LocalDate dateTo, Integer mealGrams) {
        this.meal_id = meal_id;
        this.mealName = mealName;
        this.dayOfWeek = dayOfWeek;
        this.breakfast = breakfast;
        this.desert = desert;
        this.lunch = lunch;
        this.snack = snack;
        this.dinner = dinner;
        this.foods = foods;
        this.customer = customer;
        this.mealPlan = mealPlan;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.mealGrams = mealGrams;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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

    public List<String> getDesert() {
        return desert;
    }

    public void setDesert(List<String> desert) {
        this.desert = desert;
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

    public MealPlan getMealPlan() {
        return mealPlan;
    }

    public void setMealPlan(MealPlan mealPlan) {
        this.mealPlan = mealPlan;
    }

    public void setDinner(List<String> dinner) {
        this.dinner = dinner;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public Integer getMealGrams() {
        return mealGrams;
    }

    public void setMealGrams(Integer mealGrams) {
        this.mealGrams = mealGrams;
    }
}
