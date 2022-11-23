package by.grigoryev.anastasia.repository;

import by.grigoryev.anastasia.model.NewYearTest;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewYearTestRepository extends ReactiveCrudRepository<NewYearTest, Long> {

}
