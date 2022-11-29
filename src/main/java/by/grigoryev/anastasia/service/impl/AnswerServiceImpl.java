package by.grigoryev.anastasia.service.impl;

import by.grigoryev.anastasia.model.Answer;
import by.grigoryev.anastasia.model.TelegramUser;
import by.grigoryev.anastasia.repository.AnswerRepository;
import by.grigoryev.anastasia.repository.TelegramUserRepository;
import by.grigoryev.anastasia.service.AnswerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.User;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;

    private final TelegramUserRepository telegramUserRepository;

    @Override
    public void save(User user, String message) {
        TelegramUser telegramUser = telegramUserRepository
                .findFirstByTelegramIdOrderByTimeOfRegistrationDesc(user.getId());

        Answer answer = Answer.builder()
                .firstName(telegramUser.getFirstName())
                .message(message)
                .answerTime(LocalDateTime.now())
                .telegramUserId(telegramUser.getTelegramId())
                .foreignKeyId(telegramUser.getId())
                .build();

        answerRepository.save(answer);

        log.info("AnswerServiceImpl save " + answer);
    }
}
