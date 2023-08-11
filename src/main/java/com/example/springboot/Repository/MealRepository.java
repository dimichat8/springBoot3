package com.example.springboot.Repository;

import com.example.springboot.DTO.MealDataDto;
import com.example.springboot.Entity.Customer;
import com.example.springboot.Entity.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


public interface MealRepository extends JpaRepository<Meal, Long> {
    @Query("SELECT m FROM Meal m WHERE m.customer.customer_id = :customerId")
    List<Meal> findMealDataByCustomerId(@Param("customerId") Long customerId);

    @Query("SELECT DISTINCT NEW com.example.springboot.DTO.MealDataDto( c.customer_id, m.mealPlan, c.firstName, c.lastName, m.dateFrom, m.dateTo) FROM Meal m JOIN m.customer c")
    List<MealDataDto> getAllMealData();


    @Query("SELECT DISTINCT NEW com.example.springboot.DTO.MealDataDto( c.customer_id, m.mealPlan, c.firstName, c.lastName, m.dateFrom, m.dateTo) FROM Meal m JOIN m.customer c WHERE c.customer_id = :customerId")
    List<MealDataDto> getAllMealDataForEachCustomer(@Param("customerId") Long customerId);
}
