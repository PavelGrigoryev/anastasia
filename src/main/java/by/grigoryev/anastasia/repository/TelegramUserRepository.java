package by.grigoryev.anastasia.repository;

import by.grigoryev.anastasia.model.TelegramUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelegramUserRepository extends JpaRepository<TelegramUser, Long> {

    TelegramUser findFirstByTelegramIdOrderByTimeOfRegistrationDesc(Long telegramId);

}
