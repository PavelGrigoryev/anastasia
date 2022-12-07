package by.grigoryev.anastasia.repository;

import by.grigoryev.anastasia.model.NewPollUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewPollUserRepository extends JpaRepository<NewPollUser, Long> {

    NewPollUser findFirstByTelegramIdOrderByTimeOfRegistrationDesc(Long telegramId);

}
