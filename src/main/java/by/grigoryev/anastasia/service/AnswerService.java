package by.grigoryev.anastasia.service;

import by.grigoryev.anastasia.model.Answer;
import org.telegram.telegrambots.meta.api.objects.User;
import reactor.core.publisher.Mono;

public interface AnswerService {

    Mono<Answer> save(User user, String message);

}
