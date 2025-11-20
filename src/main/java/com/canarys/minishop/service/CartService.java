package com.canarys.minishop.service;

import com.canarys.minishop.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.*;

@Service
@SessionScope
public class CartService {
    private final Map<Long, Integer> items = new LinkedHashMap<>();

    public void add(Product p) {
        items.put(p.getId(), items.getOrDefault(p.getId(), 0) + 1);
    }

    public void remove(Long productId) {
        items.remove(productId);
    }

    public Map<Long, Integer> getItems() {
        return Collections.unmodifiableMap(items);
    }

    public void clear() { items.clear(); }

    public boolean isEmpty() { return items.isEmpty(); }
}
