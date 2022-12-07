package by.grigoryev.anastasia.repository;

import by.grigoryev.anastasia.model.TelegramAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TelegramAnswerRepository extends JpaRepository<TelegramAnswer, Long> {

    List<TelegramAnswer> findAllByForeignKeyIdOrderById(Long id);

}
