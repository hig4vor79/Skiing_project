package com.skiing.controllers;

import com.skiing.beans.HttpSession;
import com.skiing.services.SkiingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/cart")
@AllArgsConstructor
public class CartController {
    private final HttpSession session;
    private final SkiingService service;

    @GetMapping
    public ModelAndView getCart(ModelAndView modelAndView) {
        if(!session.isPresent()){
            return new ModelAndView("redirect:/login");
        }
        modelAndView.addObject("sessionIsPresent", session.isPresent());
        modelAndView.addObject("cart", session.getCart());
        modelAndView.setViewName("cart.html");
        return modelAndView;
    }

    @PostMapping("/remove/{id}")
    public ModelAndView removeFromCart(@PathVariable String id) {
        session.removeFromCart(service.getById(id));
        return new ModelAndView("redirect:/cart");
    }
}
