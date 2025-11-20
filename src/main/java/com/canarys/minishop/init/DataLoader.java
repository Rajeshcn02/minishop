package com.canarys.minishop.init;

import com.canarys.minishop.model.Product;
import com.canarys.minishop.repo.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataLoader implements CommandLineRunner {

    private final ProductRepository repo;
    public DataLoader(ProductRepository repo) { this.repo = repo; }

    @Override
    public void run(String... args) {
        if (repo.count() == 0) {
            repo.save(new Product("GitHub Hoodie",
                    "Warm hoodie for devs. Limited edition.",
                    new BigDecimal("1999.00"), 20));

            repo.save(new Product("Mechanical Keyboard",
                    "Clicky RGB keyboard. Your PRs will sound faster.",
                    new BigDecimal("5499.00"), 10));

            repo.save(new Product("DevSecOps Mug",
                    "For coffee and incident retros.",
                    new BigDecimal("499.00"), 50));
        }
    }
}
