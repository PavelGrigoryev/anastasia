package by.grigoryev.anastasia.service;

import by.grigoryev.anastasia.model.Answer;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import reactor.core.publisher.Mono;

public interface AnswerService {

    Mono<Answer> save(CallbackQuery callbackQuery, String message);

}
