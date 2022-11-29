package by.grigoryev.anastasia.service;

import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

public interface TelegramUserService {

    void save(CallbackQuery callbackQuery);

}
