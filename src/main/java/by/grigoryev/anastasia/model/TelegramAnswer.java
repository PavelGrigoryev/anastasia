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
@Table(name = "telegram_answers")
public class TelegramAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "answer")
    private String message;

    @Column(name = "answer_time")
    private LocalDateTime answerTime;

    @Column(name = "telegram_id")
    private Long telegramUserId;

    @Column(name = "telegram_user_id")
    private Long foreignKeyId;

}
