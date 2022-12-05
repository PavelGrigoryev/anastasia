package by.grigoryev.anastasia.service;

import org.telegram.telegrambots.meta.api.objects.User;

public interface TelegramUserService {

    void save(User user);

}
