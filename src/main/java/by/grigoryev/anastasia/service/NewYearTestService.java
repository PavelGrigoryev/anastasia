package by.grigoryev.anastasia.service;

import by.grigoryev.anastasia.model.NewYearTest;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import reactor.core.publisher.Mono;

public interface NewYearTestService {

    Mono<NewYearTest> save(CallbackQuery callbackQuery, String answer);

}
