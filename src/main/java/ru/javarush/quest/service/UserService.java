package ru.javarush.quest.service;

import ru.javarush.quest.entity.Role;
import ru.javarush.quest.entity.User;
import ru.javarush.quest.repository.UserRepository;
import java.util.Map;
import java.util.Optional;

public class UserService {
    private final UserRepository userRepository;
    private static UserService userService;

    private UserService() {
        userRepository = UserRepository.get();
    }

    public static UserService getUserService() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    @SuppressWarnings("unused")
    public Map<Long, User> getAll() {
        return userRepository.getAll();
    }

    public Optional<User> get(long userId) {
        return userRepository.get(userId);
    }

    public Optional<User> find(String login, String password) {
        return userRepository.find(login, password);
    }

    public Optional<User> verify(String login) {
        return userRepository.verify(login);
    }

    public void doPost(String login, String password, Role role) {
        userRepository.doPost(login, password, role);
    }
}
