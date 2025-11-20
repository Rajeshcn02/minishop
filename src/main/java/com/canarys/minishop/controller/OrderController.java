package com.canarys.minishop.controller;

import com.canarys.minishop.repo.OrderRepository;
import com.canarys.minishop.service.CartService;
import com.canarys.minishop.service.OrderService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class OrderController {

    private final OrderService orderService;
    private final OrderRepository orderRepo;
    private final CartService cart;

    public OrderController(OrderService orderService, OrderRepository orderRepo, CartService cart) {
        this.orderService = orderService;
        this.orderRepo = orderRepo;
        this.cart = cart;
    }

    @PostMapping("/checkout")
    public String checkout(Authentication auth) {
        orderService.checkout(auth.getName(), cart.getItems());
        cart.clear();
        return "redirect:/orders";
    }

    @GetMapping("/orders")
    public String orders(Authentication auth, Model model) {
        model.addAttribute("orders", orderRepo.findByUsernameOrderByCreatedAtDesc(auth.getName()));
        return "orders";
    }
}
