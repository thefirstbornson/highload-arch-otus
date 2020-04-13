package ru.otus.highloadarch.service;

import org.springframework.stereotype.Service;
import ru.otus.highloadarch.domain.User;
import ru.otus.highloadarch.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserCrudServiceImpl implements UserCrudService{

    private UserRepository userRepository;

    public UserCrudServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        List<User> result = new ArrayList<>();
        userRepository.findAll().forEach(result::add);
        return result;
    }

    @Override
    public Optional<User> findById(long id) {
        return Optional.ofNullable(userRepository.findById(id));
    }
}
