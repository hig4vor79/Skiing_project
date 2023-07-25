package com.skiing.controllers;

import com.skiing.beans.HttpSession;
import com.skiing.services.SkiingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/catalog")
@AllArgsConstructor
public class CatalogController {
    private final HttpSession session;
    private final SkiingService service;

    @GetMapping
    public ModelAndView getCatalog(ModelAndView modelAndView) {
        modelAndView.addObject("flowers", service.findAll());
        modelAndView.setViewName("catalog.html");
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView getSkiing(@PathVariable String id, ModelAndView modelAndView) {
        modelAndView.addObject("flower", service.getById(id));
        modelAndView.setViewName("skiing.html");
        return modelAndView;
    }

    @PostMapping("/{id}")
    public ModelAndView addToCart(@PathVariable String id) {
        if (!session.isPresent()) {
            return new ModelAndView("redirect:/login");
        }
        session.add(service.getById(id));
        return new ModelAndView("redirect:/catalog");
    }
}
