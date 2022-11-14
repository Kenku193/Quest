package ru.javarush.quest.service;

import ru.javarush.quest.entity.Answer;
import ru.javarush.quest.entity.Question;
import ru.javarush.quest.repository.QuestRepository;

import java.util.Collection;
import java.util.Optional;

public class QuestService {
    private final QuestRepository questRepository;
    private static QuestService questService;

    private QuestService() {
        questRepository = QuestRepository.get();
    }

    public static QuestService getQuestService() {
        if (questService == null) {
            questService = new QuestService();
        }
        return questService;
    }

    @SuppressWarnings("unused")
    public Collection<Question> getAll(long questId) {
        return questRepository.getAll(questId);
    }

    public Optional<Question> get(long questId, long questionId) {
        return questRepository.get(questId, questionId);
    }

    public Question getStartQuestion(long questId) {
        return questRepository.getStartQuestion(questId);
    }

    public Collection<Answer> getAnswers(long questId, Question question) {
        return questRepository.getAnswers(questId, question);
    }
}
