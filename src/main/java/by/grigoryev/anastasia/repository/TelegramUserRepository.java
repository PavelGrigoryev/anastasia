package by.grigoryev.anastasia.repository;

import by.grigoryev.anastasia.model.TelegramUser;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface TelegramUserRepository extends ReactiveCrudRepository<TelegramUser, Long> {

    Mono<TelegramUser> findFirstByTelegramIdOrderByTimeOfRegistrationDesc(Long telegramId);

}
