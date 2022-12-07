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
@Table(name = "new_poll_questions")
public class NewPollQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;

    @Column(name = "telegram_id")
    private Long telegramId;

    @Column(name = "new_poll_title_id")
    private Long newPollTitleId;

    @Column(name = "time_of_creation")
    private LocalDateTime timeOfCreation;

}
