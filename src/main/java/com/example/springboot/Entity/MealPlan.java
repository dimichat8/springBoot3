
package com.example.springboot.Entity;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerId")
    private Customer customer;


    @OneToMany(mappedBy = "mealPlan", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Meal> meals = new ArrayList<>();


    public MealPlan() {
    }

    public MealPlan(Long mealPlanId, Customer customer, List<Meal> meals) {
        this.mealPlanId = mealPlanId;
        this.customer = customer;
        this.meals = meals;
    }

    public Long getMealPlanId() {
        return mealPlanId;
    }

    public void setMealPlanId(Long mealPlanId) {
        this.mealPlanId = mealPlanId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }
}
