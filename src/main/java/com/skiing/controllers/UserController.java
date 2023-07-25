package com.skiing.controllers;

import com.skiing.beans.HttpSession;
import com.skiing.models.entities.User;
import com.skiing.models.enums.UserRole;
import com.skiing.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping
@AllArgsConstructor
public class UserController {
    private final HttpSession session;
    private final UserService service;

    @GetMapping("/account")
    public ModelAndView getUser(ModelAndView modelAndView) {
        if (!session.isPresent()) {
            return new ModelAndView("redirect:/login");
        }
        modelAndView.addObject("user", session.getUser());
        modelAndView.setViewName("account.html");
        return modelAndView;
    }

    @PostMapping("/account")
    public ModelAndView exit() {
        session.clear();
        return new ModelAndView("redirect:/login");
    }

    @GetMapping("/signin")
    public ModelAndView signIn(@RequestParam(name = "error", defaultValue = "false") Boolean error, ModelAndView modelAndView) {
        User user = new User();
        modelAndView.addObject("error", error);
        modelAndView.addObject("user", user);
        modelAndView.setViewName("signin.html");
        return modelAndView;
    }

    @PostMapping("/signin")
    public ModelAndView createUser(@ModelAttribute User user, ModelAndView modelAndView) {
        user.setRole(UserRole.CLIENT);
        try {
            session.setUser(service.save(user));
        } catch (Exception ex) {
            return signIn(true, modelAndView);
        }
        return new ModelAndView("redirect:/catalog");
    }

    @GetMapping("/login")
    public ModelAndView logIn(ModelAndView modelAndView) {
        modelAndView.setViewName("login.html");
        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView getUser(@RequestParam String phone, @RequestParam String password, ModelAndView modelAndView) {
        session.setUser(service.getUserByPhoneAndPassword(phone, password));
        return (session.isPresent()) ? new ModelAndView("redirect:/catalog") : logIn(modelAndView);
    }
}
