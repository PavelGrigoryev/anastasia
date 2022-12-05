package by.grigoryev.anastasia.service;

import org.telegram.telegrambots.meta.api.objects.User;

public interface TelegramAnswerService {

    void save(User user, String message);

}
