package by.grigoryev.anastasia.repository;

import by.grigoryev.anastasia.model.TelegramUser;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelegramUserRepository extends ReactiveCrudRepository<TelegramUser, Long> {

}
