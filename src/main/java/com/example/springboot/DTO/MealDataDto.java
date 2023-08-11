package com.example.springboot.DTO;
import com.example.springboot.Entity.MealPlan;

import java.time.LocalDate;

public class MealDataDto {

    private Long customerId;
    private MealPlan mealPlan;
    private String customerName;
    private String customerLastName;
    private LocalDate dateFrom;
    private LocalDate dateTo;


    public MealDataDto() {
    }

    public MealDataDto(Long customerId, MealPlan mealPlan, String customerName, String customerLastName, LocalDate dateFrom, LocalDate dateTo) {
        this.customerId = customerId;
        this.mealPlan = mealPlan;
        this.customerName = customerName;
        this.customerLastName = customerLastName;;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public MealPlan getMealPlan() {
        return mealPlan;
    }

    public void setMealPlan(MealPlan mealPlan) {
        this.mealPlan = mealPlan;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
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
}
