package by.grigoryev.anastasia.repository;

import by.grigoryev.anastasia.model.NewPollQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewPollQuestionRepository extends JpaRepository<NewPollQuestion, Long> {

    NewPollQuestion findFirstByTelegramIdOrderByTimeOfCreationDesc(Long telegramId);

}
