package com.skiing.controllers;

import com.skiing.beans.HttpSession;
import com.skiing.models.entities.Skiing;
import com.skiing.models.entities.Order;
import com.skiing.models.entities.User;
import com.skiing.services.SkiingService;
import com.skiing.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {
    private final HttpSession session;
    private final OrderService orderService;
    private final SkiingService skiingService;

    @GetMapping
    public ModelAndView getOrders(ModelAndView modelAndView) {
        if (!session.isPresent()) {
            return new ModelAndView("redirect:/login");
        }
        User currentUser = session.getUser();
        List<Order> orders = orderService.getForUser(currentUser);
        modelAndView.addObject("user", currentUser);
        modelAndView.addObject("orders", orders);
        modelAndView.setViewName("orders.html");
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView getOrder(@PathVariable("id") String idOrder, ModelAndView modelAndView) {
        if (!session.isPresent()) {
            return new ModelAndView("redirect:/login");
        }
        modelAndView.addObject("order", orderService.getById(idOrder));
        modelAndView.setViewName("order.html");
        return modelAndView;
    }

    @PostMapping("/create")
    @Transactional
    public ModelAndView createOrder(@RequestParam("delivery") String deliveryAddress) {
        Set<Skiing> products = session.getCart();
        if (products.size() == 0 || skiingService.isThereEmptyProducts(products)) {
            return new ModelAndView("redirect:/cart");
        }
        products = skiingService.changeCount(products, -1);
        Order order = new Order(session.getUser(), deliveryAddress, products);
        skiingService.save(products);
        orderService.save(order);
        session.clearCart();
        return new ModelAndView("redirect:/orders");
    }

    @PostMapping("/accept/manager/{id}")
    public ModelAndView acceptManagerOrder(@PathVariable("id") String idOrder) {
        Order order = orderService.getById(idOrder);
        order.setManager(session.getUser());
        order.setEndDateManager(Date.valueOf(LocalDate.now()));
        orderService.save(order);
        return new ModelAndView("redirect:/orders");
    }

    @PostMapping("/done/{id}")
    public ModelAndView doneOrder(@PathVariable("id") String idOrder) {
        Order order = orderService.getById(idOrder);
        order.setEndDateManager(Date.valueOf(LocalDate.now()));
        order.setEndDate(Date.valueOf(LocalDate.now()));
        orderService.save(order);
        return new ModelAndView("redirect:/orders");
    }

    @PostMapping("/accept/courier/{id}")
    public ModelAndView deliveryCourierOrder(@PathVariable("id") String idOrder) {
        Order order = orderService.getById(idOrder);
        if (order.getCourier() == null) {
            order.setCourier(session.getUser());
        } else {
            order.setEndDateCourier(Date.valueOf(LocalDate.now()));
        }
        orderService.save(order);
        return new ModelAndView("redirect:/orders");
    }

    @PostMapping("/cancel/{id}")
    @Transactional
    public ModelAndView cancelOrder(@PathVariable("id") String idOrder) {
        if (!session.isPresent()) {
            return new ModelAndView("redirect:/login");
        }
        Order order = orderService.getById(idOrder);
        Set<Skiing> products = skiingService.changeCount(order.getProducts(), 1);
        order.setCanceled(true);
        skiingService.save(products);
        orderService.save(order);
        return new ModelAndView("redirect:/orders");
    }
}
