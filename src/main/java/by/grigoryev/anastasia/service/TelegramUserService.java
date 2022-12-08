package by.grigoryev.anastasia.service;

import by.grigoryev.anastasia.model.TelegramUser;
import org.telegram.telegrambots.meta.api.objects.User;

public interface TelegramUserService {

    TelegramUser save(User user);

}
