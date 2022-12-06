package by.grigoryev.anastasia.repository;

import by.grigoryev.anastasia.model.NewPollTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewPollTitleRepository extends JpaRepository<NewPollTitle, Long> {

    NewPollTitle findFirstByTelegramIdOrderByTimeOfCreationDesc(Long telegramId);

    List<NewPollTitle> findAllByTelegramId(Long telegramId);

}
