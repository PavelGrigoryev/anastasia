package by.grigoryev.anastasia.service.impl;

import by.grigoryev.anastasia.model.TelegramUser;
import by.grigoryev.anastasia.repository.TelegramUserRepository;
import by.grigoryev.anastasia.service.TelegramUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.User;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class TelegramUserServiceImpl implements TelegramUserService {

    private final TelegramUserRepository telegramUserRepository;

    @Override
    public void save(CallbackQuery callbackQuery) {
        TelegramUser telegramUser = createTelegramUser(callbackQuery);
        telegramUserRepository.save(telegramUser);
        log.info("TelegramUserServiceImpl save " + telegramUser);
    }

    private static TelegramUser createTelegramUser(CallbackQuery callbackQuery) {
        User user = callbackQuery.getFrom();
        return TelegramUser.builder()
                .telegramId(user.getId())
                .userName(user.getUserName())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .timeOfRegistration(LocalDateTime.now())
                .languageCode(user.getLanguageCode())
                .build();
    }

}
