package com.example.springboot.Excetion;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String customerNotFound) {
    }
}
