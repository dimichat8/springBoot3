package com.example.springboot.DTO;
import com.example.springboot.Entity.MealPlan;

import java.time.LocalDate;
import java.util.List;

public class MealDataDto {

    private Long customerId;
    private MealPlan mealPlan;
    private String customerName;
    private String customerLastName;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private String dayOfWeek;
    private String type;

    private List<String> breakfast;
    private List<String> desert;
    private List<String> lunch;
    private List<String> snack;
    private List<String> dinner;


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

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public void setDinner(List<String> dinner) {
        this.dinner = dinner;
    }
}
