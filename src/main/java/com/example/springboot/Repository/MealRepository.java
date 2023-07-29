package com.example.springboot.Repository;

import com.example.springboot.Entity.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {
    @Query("SELECT m FROM Meal m WHERE m.customer.customer_id = :customerId")
    List<Meal> findMealDataByCustomerId(@Param("customerId") Long customerId);
}
