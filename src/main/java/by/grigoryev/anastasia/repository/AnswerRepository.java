package by.grigoryev.anastasia.repository;

import by.grigoryev.anastasia.model.Answer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends ReactiveMongoRepository<Answer, String> {
}
