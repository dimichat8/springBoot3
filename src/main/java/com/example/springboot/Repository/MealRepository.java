package com.example.springboot.Repository;

import com.example.springboot.Entity.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {
    /*@Query("SELECT u.breakfast FROM Meal u")
    List<Meal> findBreakfast();*/
}
