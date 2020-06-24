package ru.otus.highloadarch.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import ru.otus.highloadarch.domain.Role;
import ru.otus.highloadarch.domain.User;
import ru.otus.highloadarch.read_repository.UserReadRepository;
import ru.otus.highloadarch.repository.RoleRepository;
import ru.otus.highloadarch.repository.UserRepository;

import java.sql.Connection;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserReadRepository userReadRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final CsvLoader csvLoader;


    public UserServiceImpl(UserRepository userRepository,
                           UserReadRepository userReadRepository,
                           RoleRepository roleRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder,
                           CsvLoader csvLoader) {
        this.userRepository = userRepository;
        this.userReadRepository = userReadRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.csvLoader = csvLoader;
    }

    @Override
    public User create(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Role role = roleRepository.findById(1L)
                .get();
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
        userRepository.findAll()
                .forEach(result::add);
        return result;
    }

    @Override
    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> readRepFindById(long id) {
        return userReadRepository.findById(id);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEMail(email);
    }

    @Override
    public User readRepfindByEmail(String email) {
        return userReadRepository.findByEMail(email);
    }

    @Override
    public List<User> generateUsers(Long count) {
        List<String> names = getFirstWordFromCsvString("names.csv");
        List<String> surnames = getFirstWordFromCsvString("surnames.csv");
        List<User> users = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            User user = new User();
            user.setFirstName(names.get(getRandomNumberInRange(0, names.size() - 1)));
            user.setLastName(surnames.get(getRandomNumberInRange(0, surnames.size() - 1)));
            user.setSex(Arrays.asList("м", "ж")
                    .get(new Random().nextInt(2)));
            user.setInterests(Arrays.asList("java", "c++", "docker")
                    .get(new Random().nextInt(3)));
            user.setPassword("default");
            user.setEMail(DigestUtils.md5DigestAsHex(user.getLastName()
                    .getBytes()) + "@otus.ru");
            user.setAge(new Random().nextInt(100));
            users.add(user);
        }
        return users;
    }

    @Override
    public List<User> saveAll(List<User> users) {
        return (List<User>) userRepository.saveAll(users);
    }

    private List<String> getFirstWordFromCsvString(String names) {
        return csvLoader.loadFromFile(names)
                .stream()
                .map(list -> list.get(0))
                .collect(Collectors.toList());
    }

    private static int getRandomNumberInRange(int min, int max) {
        Random r = new Random();
        return r.ints(min, (max + 1))
                .limit(1)
                .findFirst()
                .getAsInt();

    }

    @Override
    public List<User> findUsersUsingPattern(String firstNamePattern, String lastNamePattern) {
        return userRepository.findByFirstNameLikeAndLastNameLike(firstNamePattern + "%", lastNamePattern + "%");
    }

    @Override
    public List<User> readRepfindUsersUsingPattern(String firstNamePattern, String lastNamePattern) {
        return userReadRepository.findByFirstNameLikeAndLastNameLike(firstNamePattern + "%", lastNamePattern + "%");
    }


}
