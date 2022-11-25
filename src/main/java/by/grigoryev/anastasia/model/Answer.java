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

    @Column("first_name")
    private String firstName;

    @Column("answer")
    private String message;

    @Column("test_start_time")
    private LocalDateTime testStartTime;

    @Column("telegram_user_id")
    private Long telegramUserId;

}
