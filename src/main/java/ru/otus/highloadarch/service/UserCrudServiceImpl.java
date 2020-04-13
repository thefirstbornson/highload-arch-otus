package ru.otus.highloadarch.service;

import org.springframework.stereotype.Service;
import ru.otus.highloadarch.domain.Role;
import ru.otus.highloadarch.domain.User;
import ru.otus.highloadarch.repository.RoleRepository;
import ru.otus.highloadarch.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserCrudServiceImpl implements UserCrudService{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


    public UserCrudServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public User create(User user) {
        Role role = roleRepository.findById(1L).get();
        user.addUserRole(role);
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
        return userRepository.findById(id);
    }
}
