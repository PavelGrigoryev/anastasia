package by.grigoryev.anastasia.service;

public interface NewPollService {

    void createTitle(String title, Long telegramId);

    void createQuestion(String question, Long telegramId);

    void createAnswer(String answer, Long telegramId);

}
