package com.example.springboot.Repository;

import com.example.springboot.Entity.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {
    @Query("SELECT m.meal_id, m.mealName FROM Meal m JOIN Customer c where c.customer_id = m.customer.customer_id")
    List<Object[]> findMealDataByCustomerId(@Param("customerId") Long customerId);
}
