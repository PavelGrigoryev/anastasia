package by.grigoryev.anastasia.service.impl;

import by.grigoryev.anastasia.model.TelegramAnswer;
import by.grigoryev.anastasia.model.TelegramUser;
import by.grigoryev.anastasia.repository.TelegramAnswerRepository;
import by.grigoryev.anastasia.repository.TelegramUserRepository;
import by.grigoryev.anastasia.service.TelegramAnswerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.User;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class TelegramAnswerServiceImpl implements TelegramAnswerService {

    private final TelegramAnswerRepository telegramAnswerRepository;

    private final TelegramUserRepository telegramUserRepository;

    @Override
    public void save(User user, String message) {
        TelegramUser telegramUser = telegramUserRepository
                .findFirstByTelegramIdOrderByTimeOfRegistrationDesc(user.getId());

        TelegramAnswer telegramAnswer = TelegramAnswer.builder()
                .firstName(telegramUser.getFirstName())
                .message(message)
                .answerTime(LocalDateTime.now())
                .telegramUserId(telegramUser.getTelegramId())
                .foreignKeyId(telegramUser.getId())
                .build();

        telegramAnswerRepository.save(telegramAnswer);

        log.info("TelegramAnswerServiceImpl save " + telegramAnswer);
    }
}
