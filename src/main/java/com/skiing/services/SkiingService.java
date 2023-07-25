package com.skiing.services;

import com.skiing.interfaces.RepositoryService;
import com.skiing.models.entities.Skiing;
import com.skiing.repositories.SkiingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SkiingService implements RepositoryService<Skiing, String> {
    private final SkiingRepository repository;

    @Override
    public Skiing save(Skiing product) {
        return repository.save(product);
    }

    @Override
    public List<Skiing> save(Iterable<Skiing> products) {
        return repository.saveAll(products);
    }

    @Override
    public List<Skiing> findAll() {
        return repository.findAll();
    }

    @Override
    public Skiing getById(String id) {
        return repository.getReferenceById(id);
    }

    public Set<Skiing> changeCount(Set<Skiing> products, int value) {
        return products.stream()
                .peek(e -> e.setCount(e.getCount() + value))
                .collect(Collectors.toSet());
    }

    public boolean isThereEmptyProducts(Set<Skiing> products) {
        return products.stream().anyMatch(e -> e.getCount() <= 0);
    }
}
