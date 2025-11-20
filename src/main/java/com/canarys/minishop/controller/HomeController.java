package com.canarys.minishop.controller;

import com.canarys.minishop.repo.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final ProductRepository repo;
    public HomeController(ProductRepository repo) { this.repo = repo; }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("products", repo.findAll());
        return "index";
    }

    @GetMapping("/login")
    public String login() { return "login"; }
}
