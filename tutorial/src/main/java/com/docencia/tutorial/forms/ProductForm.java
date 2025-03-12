package com.docencia.tutorial.forms;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ProductForm {

    @NotEmpty(message = "The product name is required")
    private String name;

    @NotNull(message = "The price is required")
    @Positive(message = "The price must be greater than zero")
    private Double price;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
