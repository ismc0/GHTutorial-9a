package com.docencia.tutorial.models;

import jakarta.persistence.*;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public Comment() { }

    public Comment(String description, Product product) {
        this.description = description;
        this.product = product;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", product=" + (product != null ? product.getId() : null) +
                '}';
    }
}