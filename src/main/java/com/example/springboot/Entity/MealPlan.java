
package com.example.springboot.Entity;

import jakarta.persistence.*;

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


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mealId")
    private Meal meal;

    public MealPlan() {
    }

    public MealPlan(Long mealPlanId, Customer customer, Meal meal) {
        this.mealPlanId = mealPlanId;
        this.customer = customer;
        this.meal = meal;
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

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }
}
