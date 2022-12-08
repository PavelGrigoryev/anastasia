package by.grigoryev.anastasia.service.impl;

import by.grigoryev.anastasia.model.TelegramUser;
import by.grigoryev.anastasia.repository.TelegramUserRepository;
import by.grigoryev.anastasia.service.TelegramUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.User;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class TelegramUserServiceImpl implements TelegramUserService {

    private final TelegramUserRepository telegramUserRepository;

    @Override
    public TelegramUser save(User user) {
        TelegramUser telegramUser = telegramUserRepository.save(createTelegramUser(user));
        log.info("TelegramUserServiceImpl save " + telegramUser);
        return telegramUser;
    }

    private static TelegramUser createTelegramUser(User user) {
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
