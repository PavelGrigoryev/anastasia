package by.grigoryev.anastasia.service;

import by.grigoryev.anastasia.dto.TelegramUserDto;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import reactor.core.publisher.Mono;

public interface TelegramUserService {

    Mono<TelegramUserDto> save(CallbackQuery callbackQuery);

}
