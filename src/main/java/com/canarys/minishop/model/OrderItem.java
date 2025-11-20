package com.canarys.minishop.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class OrderItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;
    private String productName;
    private BigDecimal price;
    private int quantity;

    public OrderItem() {}

    public OrderItem(Long productId, String productName, BigDecimal price, int quantity) {
        this.productId = productId; this.productName = productName;
        this.price = price; this.quantity = quantity;
    }

    public Long getProductId() { return productId; }
    public String getProductName() { return productName; }
    public BigDecimal getPrice() { return price; }
    public int getQuantity() { return quantity; }
}
