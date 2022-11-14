package ru.javarush.quest.entity;

import ru.javarush.quest.repository.GameStatistics;

public class User {
    private final String login;
    private String password;
    private Role role;
    private final GameStatistics gameStatistics;

    public User(String login, String password, Role role, GameStatistics gameStatistics) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.gameStatistics = gameStatistics;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public GameStatistics getGameStatistics() {
        return gameStatistics;
    }

    @SuppressWarnings("unused")
    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
