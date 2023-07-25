package com.skiing.services;

import com.skiing.interfaces.RepositoryService;
import com.skiing.models.entities.User;
import com.skiing.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements RepositoryService<User, String> {
    private final BCryptPasswordEncoder encoder;
    private final UserRepository repository;

    @Override
    public User save(User user) {
        encodePassword(user);
        return repository.save(user);
    }

    @Override
    public List<User> save(Iterable<User> users) {
        for (User user : users) {
            encodePassword(user);
        }
        return repository.saveAll(users);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User getById(String id) {
        return repository.getReferenceById(id);
    }

    public User getUserByPhoneAndPassword(String phone, String password) {
        User user = repository.getByPhone(phone);
        return (user != null && encoder.matches(password, user.getPassword())) ? user : null;
    }

    private void encodePassword(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
    }
}
