package by.grigoryev.anastasia.service;

import org.telegram.telegrambots.meta.api.objects.User;

public interface AnswerService {

    void save(User user, String message);

}
