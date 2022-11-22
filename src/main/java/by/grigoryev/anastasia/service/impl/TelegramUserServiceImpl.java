package by.grigoryev.anastasia.service.impl;

import by.grigoryev.anastasia.model.TelegramUser;
import by.grigoryev.anastasia.repository.TelegramUserRepository;
import by.grigoryev.anastasia.service.TelegramUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.User;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class TelegramUserServiceImpl implements TelegramUserService {

    private final TelegramUserRepository telegramUserRepository;

    @Override
    public Mono<TelegramUser> save(CallbackQuery callbackQuery) {
        TelegramUser telegramUser = createTelegramUser(callbackQuery);
        return telegramUserRepository.save(telegramUser)
                .log("save");
    }

    private static TelegramUser createTelegramUser(CallbackQuery callbackQuery) {
        User user = callbackQuery.getFrom();
        return TelegramUser.builder()
                .telegramUserId(user.getId())
                .userName(user.getUserName())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .timeOfRegistration(LocalDateTime.now())
                .languageCode(user.getLanguageCode())
                .build();
    }

}
