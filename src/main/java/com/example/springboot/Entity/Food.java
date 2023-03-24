package com.example.springboot.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "food")
public class Food {

    @Id
    @SequenceGenerator(name = "food_id_sequence", sequenceName = "food_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "food_id_sequence")
    @Column(name = "food_id")
    private Long food_id;

    @Column(name = "name")
    private String name;
    @Column(name = "grams")
    private Float grams;
    @Column(name = "sodium")
    private Integer sodium;
    @Column(name = "potassium")
    private Float potassium;
    @Column(name = "calcium")
    private Float calcium;
    @Column(name = "magnesium")
    private Float magnesium;
    @Column(name = "phosphorus")
    private Float phosphorus;
    @Column(name = "iron")
    private Float iron;
    @Column(name = "zinc")
    private Float zinc;
    @Column(name = "retinol")
    private Float retinol;
    @Column(name = "carotene")
    private Float carotene;
    @Column(name = "thiamine")
    private Float thiamine;
    @Column(name = "riboflavin")
    private Float riboflavin;

    @Column(name = "vitaminB6")
    private Float vitaminB6;
    @Column(name = "vitaminE")
    private Float vitaminE;
    @Column(name = "vitaminC")
    private Float vitaminC;
    @Column(name = "calories")
    private Float calories;

    @Column(name = "totalFat")
    private Float totalFat;
    @Column(name = "saturateFat")
    private Float saturateFat;
    @Column(name = "cholesterol")
    private Float cholesterol;
    @Column(name = "totalCarbs")
    private Float totalCarbs;
    @Column(name = "fiber")
    private Float fiber;
    @Column(name = "sugar")
    private Float sugar;
    @Column(name = "protein")
    private Float protein;


    public Food(Long food_id, Float grams, String name, Integer sodium, Float potassium, Float calcium, Float magnesium, Float phosphorus, Float iron, Float zinc, Float retinol, Float carotene, Float thiamine, Float riboflavin, Float vitaminB6, Float vitaminE, Float vitaminC, Float calories, Float totalFat, Float saturateFat, Float cholesterol, Float totalCarbs, Float fiber, Float sugar, Float protein) {
        this.food_id = food_id;
        this.name = name;
        this.grams= grams;
        this.sodium = sodium;
        this.potassium = potassium;
        this.calcium = calcium;
        this.magnesium = magnesium;
        this.phosphorus = phosphorus;
        this.iron = iron;
        this.zinc = zinc;
        this.retinol = retinol;
        this.carotene = carotene;
        this.thiamine = thiamine;
        this.riboflavin = riboflavin;
        this.vitaminB6 = vitaminB6;
        this.vitaminE = vitaminE;
        this.vitaminC = vitaminC;
        this.calories = calories;
        this.totalFat = totalFat;
        this.saturateFat = saturateFat;
        this.cholesterol = cholesterol;
        this.totalCarbs = totalCarbs;
        this.fiber = fiber;
        this.sugar = sugar;
        this.protein = protein;
    }

    public Food() {

    }

    public Long getFood_id() {
        return food_id;
    }

    public void setFood_id(Long food_id) {
        this.food_id = food_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getGrams() {
        return grams;
    }

    public void setGrams(Float grams) {
        this.grams = grams;
    }

    public Integer getSodium() {
        return sodium;
    }

    public void setSodium(Integer sodium) {
        this.sodium = sodium;
    }

    public Float getPotassium() {
        return potassium;
    }

    public void setPotassium(Float potassium) {
        this.potassium = potassium;
    }

    public Float getCalcium() {
        return calcium;
    }

    public void setCalcium(Float calcium) {
        this.calcium = calcium;
    }

    public Float getMagnesium() {
        return magnesium;
    }

    public void setMagnesium(Float magnesium) {
        this.magnesium = magnesium;
    }

    public Float getPhosphorus() {
        return phosphorus;
    }

    public void setPhosphorus(Float phosphorus) {
        this.phosphorus = phosphorus;
    }

    public Float getIron() {
        return iron;
    }

    public void setIron(Float iron) {
        this.iron = iron;
    }

    public Float getZinc() {
        return zinc;
    }

    public void setZinc(Float zinc) {
        this.zinc = zinc;
    }

    public Float getRetinol() {
        return retinol;
    }

    public void setRetinol(Float retinol) {
        this.retinol = retinol;
    }

    public Float getCarotene() {
        return carotene;
    }

    public void setCarotene(Float carotene) {
        this.carotene = carotene;
    }

    public Float getThiamine() {
        return thiamine;
    }

    public void setThiamine(Float thiamine) {
        this.thiamine = thiamine;
    }

    public Float getRiboflavin() {
        return riboflavin;
    }

    public void setRiboflavin(Float riboflavin) {
        this.riboflavin = riboflavin;
    }

    public Float getVitaminB6() {
        return vitaminB6;
    }

    public void setVitaminB6(Float vitaminB6) {
        this.vitaminB6 = vitaminB6;
    }

    public Float getVitaminE() {
        return vitaminE;
    }

    public void setVitaminE(Float vitaminE) {
        this.vitaminE = vitaminE;
    }

    public Float getVitaminC() {
        return vitaminC;
    }

    public void setVitaminC(Float vitaminC) {
        this.vitaminC = vitaminC;
    }

    public Float getCalories() {
        return calories;
    }

    public void setCalories(Float calories) {
        this.calories = calories;
    }

    public Float getTotalFat() {
        return totalFat;
    }

    public void setTotalFat(Float totalFat) {
        this.totalFat = totalFat;
    }

    public Float getSaturateFat() {
        return saturateFat;
    }

    public void setSaturateFat(Float saturateFat) {
        this.saturateFat = saturateFat;
    }

    public Float getCholesterol() {
        return cholesterol;
    }

    public void setCholesterol(Float cholesterol) {
        this.cholesterol = cholesterol;
    }

    public Float getTotalCarbs() {
        return totalCarbs;
    }

    public void setTotalCarbs(Float totalCarbs) {
        this.totalCarbs = totalCarbs;
    }

    public Float getFiber() {
        return fiber;
    }

    public void setFiber(Float fiber) {
        this.fiber = fiber;
    }

    public Float getSugar() {
        return sugar;
    }

    public void setSugar(Float sugar) {
        this.sugar = sugar;
    }

    public Float getProtein() {
        return protein;
    }

    public void setProtein(Float protein) {
        this.protein = protein;
    }
}




