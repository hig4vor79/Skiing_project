package com.skiing.beans;

import com.skiing.models.entities.Skiing;
import com.skiing.models.entities.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.HashSet;
import java.util.Set;


@Component
@SessionScope
public class HttpSession {
    @Getter
    @Setter
    private User user;
    private final Set<Skiing> products = new HashSet<>();

    public boolean isPresent() {
        return user != null;
    }

    public void clear() {
        clearUser();
        clearCart();
    }

    public void clearUser() {
        user = null;
    }

    public void clearCart() {
        products.clear();
    }

    public void add(Skiing product) {
        products.add(product);
    }

    public Set<Skiing> getCart() {
        return products;
    }

    public void removeFromCart(Skiing product) {
        products.remove(product);
    }
}
