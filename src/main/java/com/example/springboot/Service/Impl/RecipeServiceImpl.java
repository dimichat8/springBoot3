/*
package com.example.springboot.Service.Impl;

import com.example.springboot.Entity.Recipe;
import com.example.springboot.Repository.RecipeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RecipeServiceImpl implements RecipeService{

   RecipeRepository recipeRepository;

    @Override
    public Recipe save(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    @Override
    public List<Recipe> findRecipesByName(String name) {
        List<Recipe> recipes = new ArrayList<Recipe>();
        for(Recipe r : recipeRepository.findAll()) {
            if ()
        }
        return null;
    }

    @Override
    public Optional<Recipe> findByID(long recipe_id) {
        return recipeRepository.findById(recipe_id);
    }

    @Override
    public void deleteById(Long recipe_id) {
        recipeRepository.deleteById(recipe_id);

    }

    @Override
    public List<Recipe> searchRecipes(String name, String query) {
        List<Recipe> recipes =new ArrayList<Recipe>();
        for(Recipe recip : recipeRepository.findAll()) {
            if (..)
            recipes.add(recip);
        }
            return recipes;
    }
}
*/
