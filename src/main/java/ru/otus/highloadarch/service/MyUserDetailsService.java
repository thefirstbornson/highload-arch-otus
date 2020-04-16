package ru.otus.highloadarch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.highloadarch.domain.Role;
import ru.otus.highloadarch.domain.User;
import ru.otus.highloadarch.domain.UserRole;
import ru.otus.highloadarch.repository.RoleRepository;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class MyUserDetailsService
        implements UserDetailsService {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) {
        User user = userService.findByEmail(email);
        Iterable<Long> userRoleIds = user.getRoles().stream().map(UserRole::getRole).collect(Collectors.toList());
        Set<Role> userRoles = StreamSupport
                .stream(roleRepository.findAllById(userRoleIds).spliterator(), false).collect(Collectors.toSet());
        List<GrantedAuthority> authorities = getUserAuthority(userRoles);
        return buildUserForAuthentication(user, authorities);
    }

    private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) {
        Set<GrantedAuthority> roles = new HashSet<>();
        for (Role role : userRoles) {
            roles.add(new SimpleGrantedAuthority(role.getRole()));
        }
        return new ArrayList<>(roles);
    }

    private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getEMail(), user.getPassword(),
                true, true, true, true, authorities);
    }
}
