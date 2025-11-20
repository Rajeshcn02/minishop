package com.canarys.minishop.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(length = 2000)
    private String description;

    private BigDecimal price;
    private int stock;

    public Product() {}

    public Product(String name, String description, BigDecimal price, int stock) {
        this.name = name; this.description = description;
        this.price = price; this.stock = stock;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String n) { this.name = n; }
    public String getDescription() { return description; }
    public void setDescription(String d) { this.description = d; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal p) { this.price = p; }
    public int getStock() { return stock; }
    public void setStock(int s) { this.stock = s; }
}
