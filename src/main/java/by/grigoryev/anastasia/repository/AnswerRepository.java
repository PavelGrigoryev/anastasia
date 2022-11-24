package by.grigoryev.anastasia.repository;

import by.grigoryev.anastasia.model.Answer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends ReactiveCrudRepository<Answer, Long> {
}
