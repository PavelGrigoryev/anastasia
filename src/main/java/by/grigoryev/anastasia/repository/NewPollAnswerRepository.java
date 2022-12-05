package by.grigoryev.anastasia.repository;

import by.grigoryev.anastasia.model.NewPollAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewPollAnswerRepository extends JpaRepository<NewPollAnswer, Long> {

}
