package by.grigoryev.anastasia.repository;

import by.grigoryev.anastasia.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    List<Answer> findAllByForeignKeyIdOrderById(Long id);

}
