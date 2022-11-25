package by.grigoryev.anastasia.repository;

import by.grigoryev.anastasia.model.TelegramUser;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface TelegramUserRepository extends ReactiveMongoRepository<TelegramUser, String> {

    Mono<TelegramUser> findFirstByTelegramIdOrderByTimeOfRegistrationDesc(Long telegramId);

}
