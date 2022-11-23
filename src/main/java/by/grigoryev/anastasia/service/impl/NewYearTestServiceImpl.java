package by.grigoryev.anastasia.service.impl;

import by.grigoryev.anastasia.model.NewYearTest;
import by.grigoryev.anastasia.repository.NewYearTestRepository;
import by.grigoryev.anastasia.service.NewYearTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.User;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NewYearTestServiceImpl implements NewYearTestService {

    private final NewYearTestRepository newYearTestRepository;

    @Override
    public Mono<NewYearTest> save(CallbackQuery callbackQuery, String answer) {
        NewYearTest newYearTest = createNewYearTest(callbackQuery, answer);
        return newYearTestRepository.save(newYearTest)
                .log("NewYearTestServiceImpl save");
    }

    private static NewYearTest createNewYearTest(CallbackQuery callbackQuery, String answer) {
        User user = callbackQuery.getFrom();
        return NewYearTest.builder()
                .telegramUserId(user.getId())
                .telegramUserFirstName(user.getFirstName())
                .answer(answer)
                .timeOfAnswer(LocalDateTime.now())
                .build();
    }
}
