package by.grigoryev.anastasia.service;

import org.telegram.telegrambots.meta.api.objects.User;

public interface NewPollService {

    void createTitle(String title, Long telegramId);

    void createQuestion(String question, Long telegramId);

    void createAnswer(String answer, Long telegramId);

    void save(User user);

}
