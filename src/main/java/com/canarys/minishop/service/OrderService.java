package com.canarys.minishop.service;

import com.canarys.minishop.model.*;
import com.canarys.minishop.repo.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    private final ProductRepository productRepo;
    private final OrderRepository orderRepo;

    public OrderService(ProductRepository productRepo, OrderRepository orderRepo) {
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
    }

    @Transactional
    public Order checkout(String username, java.util.Map<Long, Integer> cartItems) {
        Order order = new Order(username);

        for (var entry : cartItems.entrySet()) {
            Product p = productRepo.findById(entry.getKey()).orElseThrow();
            int qty = entry.getValue();
            if (p.getStock() < qty) {
                throw new IllegalStateException("Out of stock for " + p.getName());
            }
            p.setStock(p.getStock() - qty);
            order.addItem(new OrderItem(p.getId(), p.getName(), p.getPrice(), qty));
        }

        return orderRepo.save(order);
    }
}
