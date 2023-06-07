package com.example.springboot.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "CustomerInfo")
public class CustomerInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customerinfo_id")
    public Long customerinfo_id;
    @Column(name = "createDate")
    private LocalDateTime createDate = LocalDateTime.now();
    @Column(name = "height")
    private Float height;
    @Column(name = "water")
    private Float water;
    @Column(name = "weight")
    private Float weight;
    @Column(name = "muscleMass")
    private Float muscleMass;
    @Column(name = "bodyFatMass")
    private Float bodyFatMass;
    @Column(name = "fat")
    private Float fat;

    @ManyToOne/*(fetch = FetchType.LAZY)*/
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public CustomerInfo(Long customerinfo_id, Float height, Float water, Float weight, Float muscleMass, Float bodyFatMass, Float fat, Customer customer) {
        this.customerinfo_id = customerinfo_id;
        this.height = height;
        this.water = water;
        this.weight = weight;
        this.muscleMass = muscleMass;
        this.bodyFatMass = bodyFatMass;
        this.fat = fat;
        this.customer = customer;
    }

    public CustomerInfo() {

    }


    public Long getCustomerInfo_id() {
        return customerinfo_id;
    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public Float getWater() {
        return water;
    }

    public void setWater(Float water) {
        this.water = water;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Float getMuscleMass() {
        return muscleMass;
    }

    public void setMuscleMass(Float muscleMass) {
        this.muscleMass = muscleMass;
    }

    public Float getBodyFatMass() {
        return bodyFatMass;
    }

    public void setBodyFatMass(Float bodyFatMass) {
        this.bodyFatMass = bodyFatMass;
    }

    public Float getFat() {
        return fat;
    }

    public void setFat(Float fat) {
        this.fat = fat;
    }

    public Customer getCustomer() {
    return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }
}