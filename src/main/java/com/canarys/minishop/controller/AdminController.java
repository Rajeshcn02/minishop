package com.canarys.minishop.controller;

import com.canarys.minishop.model.Product;
import com.canarys.minishop.repo.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
@RequestMapping("/admin/products")
public class AdminController {

    private final ProductRepository repo;
    public AdminController(ProductRepository repo) { this.repo = repo; }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("products", repo.findAll());
        return "admin-products";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("product", new Product());
        return "admin-product-form";
    }

    @PostMapping
    public String create(@RequestParam String name,
                         @RequestParam String description,
                         @RequestParam BigDecimal price,
                         @RequestParam int stock) {
        repo.save(new Product(name, description, price, stock));
        return "redirect:/admin/products";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("product", repo.findById(id).orElseThrow());
        return "admin-product-form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @RequestParam String name,
                         @RequestParam String description,
                         @RequestParam BigDecimal price,
                         @RequestParam int stock) {
        Product p = repo.findById(id).orElseThrow();
        p.setName(name); p.setDescription(description);
        p.setPrice(price); p.setStock(stock);
        repo.save(p);
        return "redirect:/admin/products";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        repo.deleteById(id);
        return "redirect:/admin/products";
    }
}
