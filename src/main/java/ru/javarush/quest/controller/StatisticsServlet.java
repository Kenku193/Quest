package ru.javarush.quest.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.javarush.quest.entity.Game;
import ru.javarush.quest.entity.User;
import ru.javarush.quest.repository.GameStatistics;
import ru.javarush.quest.util.Jsp;

import java.io.IOException;
import java.util.Map;

@WebServlet("/stat")
public class StatisticsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        GameStatistics gameStatistics = user.getGameStatistics();
        Map<Long, Game> games = gameStatistics.getGames();
        Game spacegame = games.get(1L);
        Game javagame = games.get(2L);
        req.setAttribute("spacegame", spacegame);
        req.setAttribute("javagame", javagame);
        Jsp.reqRespForward(req, resp, "statistics");
    }
}
