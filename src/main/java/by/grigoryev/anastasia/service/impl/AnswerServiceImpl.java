package by.grigoryev.anastasia.service.impl;

import by.grigoryev.anastasia.model.Answer;
import by.grigoryev.anastasia.repository.AnswerRepository;
import by.grigoryev.anastasia.repository.TelegramUserRepository;
import by.grigoryev.anastasia.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.User;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;

    private final TelegramUserRepository telegramUserRepository;

    @Override
    public Mono<Answer> save(CallbackQuery callbackQuery, String message) {
        User user = callbackQuery.getFrom();

        return telegramUserRepository.findFirstByTelegramIdOrderByTimeOfRegistrationDesc(user.getId())
                .flatMap(telegramUser -> {
                    Answer answer = Answer.builder()
                            .message(message)
                            .timeOfAnswer(telegramUser.getTimeOfRegistration())
                            .telegramUserId(user.getId())
                            .build();
                    return answerRepository.save(answer);
                })
                .log("AnswerServiceImpl save");
    }
}
