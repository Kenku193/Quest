package ru.javarush.quest.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.javarush.quest.entity.Answer;
import ru.javarush.quest.entity.Question;
import ru.javarush.quest.entity.User;
import ru.javarush.quest.service.QuestService;
import ru.javarush.quest.util.Jsp;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

@WebServlet("/space-quest")
public class SpaceQuestServlet extends HttpServlet {
    private final QuestService questService = QuestService.getQuestService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = getId(req);
        if(id < 0) {
            getStartQuestion(req, resp);
        } else {
            getNextQuestion(req, resp, id);
        }
    }

    private void getNextQuestion(HttpServletRequest req, HttpServletResponse resp, long id) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");

        Optional<Question> optionalQuestion = questService.get(1L, id);
        if (optionalQuestion.isPresent()) {
            Question nextQuestion = optionalQuestion.get();
            Collection<Answer> answers = questService.getAnswers(1L, nextQuestion);

            if(answers.isEmpty()) {
                user.getGameStatistics().setGamesCount(1L);
                if(nextQuestion.isWon()) {
                    user.getGameStatistics().setGamesWon(1L);
                }
            }

            req.getSession().setAttribute("user", user);
            setQuestionAnswersAndForward(req, resp, nextQuestion, answers);
        }
    }

    private void setQuestionAnswersAndForward(HttpServletRequest req, HttpServletResponse resp, Question nextQuestion, Collection<Answer> answers) throws ServletException, IOException {
        req.setAttribute("answers", answers);
        req.setAttribute("question", nextQuestion);

        if(answers.isEmpty()) {
            req.setAttribute("end", true);
        }

        Jsp.reqRespForward(req, resp, "spacequest");
    }

    private void getStartQuestion(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Question startQuestion = questService.getStartQuestion(1L);
        Collection<Answer> answers = questService.getAnswers(1L, startQuestion);
        setQuestionAnswersAndForward(req, resp, startQuestion, answers);
    }

    private long getId(HttpServletRequest req) {
        String id = req.getParameter("id");
        if(id == null || id.isBlank()) {
            return -1;
        }
        boolean isNumeric = id.chars().allMatch(Character::isDigit);
        return isNumeric ? Long.parseLong(id) : 0;
    }
}
