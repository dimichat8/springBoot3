package com.example.springboot.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Customer")
public class Customer {
    @Id
    @SequenceGenerator(name = "customer_id_sequence", sequenceName = "customer_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "customer_id_sequence")
    @Column(name = "customer_id")
    private Long customer_id;

    @NotBlank(message = "First Name can not be empty")
    @Column(name = "firstName")
    private String firstName;

    @NotBlank(message = "Last Name can not be empty")
    @Column(name = "lastName")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "city")
    private String city;

    @Column(name = "address")
    private String address;

    @NotBlank(message = "Mobile Phone can not be empty")
    @Column(name = "phone")
    private String phone;

    @NotNull(message = "Birthday can not be empty")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "gender")
    private String gender;

    private Integer age;


    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL,  orphanRemoval = true)
    private List<CustomerInfo> customerInfos = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<Food> foods = new ArrayList<>();

    @OneToMany(mappedBy = "customer",fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Meal> meals = new ArrayList<>();


    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MealPlan> mealPlans = new ArrayList<>();

    public Customer(Long customer_id, String firstName, String lastName, String email, String address, String city, String phone, LocalDate birthday, String gender, List<CustomerInfo> customerInfos, List<Food> foods, List<Meal> meals, List<MealPlan> mealPlans) {
        this.customer_id = customer_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.city = city;
        this.phone = phone;
        this.birthday = birthday;
        this.gender = gender;
        this.customerInfos = customerInfos;
        this.foods = foods;
        this.meals = meals;
        this.mealPlans = mealPlans;
    }

    public Customer() {

    }

    public Customer(CustomerInfo customerInfos) {
        this.customerInfos = (List<CustomerInfo>) customerInfos;
    }


    public Long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Long customer_id) {
        this.customer_id = customer_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String
    getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getBirthday() {
        return birthday;
    }
    public int getAge() {
        LocalDate now = LocalDate.now();
        Period period = Period.between(birthday, now);

        return period.getYears();
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<CustomerInfo> getCustomerInfos() {
        return customerInfos;
    }

    public void setCustomerInfos(List<CustomerInfo> customerInfos) {
        this.customerInfos = customerInfos;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }

    public List<Meal> getMeal() {
        return meals;
    }

    public void setMeal(List<Meal> meal) {
        this.meals = meal;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    public List<MealPlan> getMealPlans() {
        return mealPlans;
    }

    public void setMealPlans(List<MealPlan> mealPlans) {
        this.mealPlans = mealPlans;
    }
}

