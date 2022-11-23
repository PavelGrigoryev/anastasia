package by.grigoryev.anastasia.service;

import by.grigoryev.anastasia.model.TelegramUser;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import reactor.core.publisher.Mono;

public interface TelegramUserService {

    Mono<TelegramUser> save(CallbackQuery callbackQuery, String answer);

}
