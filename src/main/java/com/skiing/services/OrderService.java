package com.skiing.services;

import com.skiing.interfaces.RepositoryService;
import com.skiing.models.entities.Order;
import com.skiing.models.entities.Skiing;
import com.skiing.models.entities.User;
import com.skiing.models.enums.UserRole;
import com.skiing.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService implements RepositoryService<Order, String> {
    private final OrderRepository repository;

    @Override
    public Order save(Order order) {
        return repository.save(order);
    }

    @Override
    public List<Order> save(Iterable<Order> orders) {
        return repository.saveAll(orders);
    }

    @Override
    public List<Order> findAll() {
        return repository.findAll();
    }

    @Override
    public Order getById(String id) {
        return repository.getReferenceById(id);
    }

    private List<Order> getByClientId(String id) {
        return repository.getByClientId(id);
    }

    private List<Order> getForManager(String managerId) {
        return repository.getForManager(managerId);
    }

    private List<Order> getForCourier() {
        return repository.getForCourier();
    }

    public List<Order> getForUser(User user) {
        if (user.getRole() == UserRole.MANAGER) {
            return getForManager(user.getId());
        } else if (user.getRole() == UserRole.COURIER) {
            return getForCourier();
        }
        return getByClientId(user.getId());
    }
}
