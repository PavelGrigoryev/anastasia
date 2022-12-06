package by.grigoryev.anastasia.repository;

import by.grigoryev.anastasia.model.NewPollAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewPollAnswerRepository extends JpaRepository<NewPollAnswer, Long> {

    List<NewPollAnswer> findAllByNewPollQuestionId(Long newPollQuestionId);

}
