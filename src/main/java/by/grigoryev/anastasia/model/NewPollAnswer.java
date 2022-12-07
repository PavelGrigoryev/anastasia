package by.grigoryev.anastasia.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "new_poll_answers")
public class NewPollAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String answer;

    @Column(name = "telegram_id")
    private Long telegramId;

    @Column(name = "new_poll_question_id")
    private Long newPollQuestionId;

    @Column(name = "time_of_creation")
    private LocalDateTime timeOfCreation;

}
