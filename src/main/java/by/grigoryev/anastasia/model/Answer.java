package by.grigoryev.anastasia.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("telegram_answers")
public class Answer {

    @Id
    private Long id;

    @Column("answer")
    private String message;

    @Column("time_of_answer")
    private LocalDateTime timeOfAnswer;

    @Column("telegram_user_id")
    private Long telegramUserId;

}
