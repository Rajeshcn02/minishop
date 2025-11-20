package com.canarys.minishop.controller;

import com.canarys.minishop.repo.ProductRepository;
import com.canarys.minishop.service.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final ProductRepository productRepo;
    private final CartService cart;

    public CartController(ProductRepository productRepo, CartService cart) {
        this.productRepo = productRepo;
        this.cart = cart;
    }

    @PostMapping("/add/{id}")
    public String add(@PathVariable Long id) {
        cart.add(productRepo.findById(id).orElseThrow());
        return "redirect:/cart";
    }

    @PostMapping("/remove/{id}")
    public String remove(@PathVariable Long id) {
        cart.remove(id);
        return "redirect:/cart";
    }

    @GetMapping
    public String view(Model model) {
        var detailed = cart.getItems().entrySet().stream()
                .map(e -> new Object[]{
                        productRepo.findById(e.getKey()).orElseThrow(),
                        e.getValue()
                }).toList();

        model.addAttribute("items", detailed);
        model.addAttribute("empty", cart.isEmpty());
        return "cart";
    }
}
