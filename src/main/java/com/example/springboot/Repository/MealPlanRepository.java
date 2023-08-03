package com.example.springboot.Repository;

import com.example.springboot.Entity.Customer;
import com.example.springboot.Entity.MealPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MealPlanRepository extends JpaRepository<MealPlan, Long> {
    @Query("SELECT distinct m.mealPlan, m.dateFrom , m.dateTo, m.customer.customer_id  FROM MealPlan mp JOIN Meal m WHERE mp.customer = :customer AND m.dateFrom <= :dateTo AND m.dateTo >= :dateFrom")
    List<Object[]> findMealPlanByCustomerAndDateRange(
                                                @Param("customer") Customer customer,
                                                @Param("dateFrom") LocalDate dateFrom,
                                                @Param("dateTo") LocalDate dateTo);
}
