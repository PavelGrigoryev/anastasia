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
@Table("new_year_tests")
public class NewYearTest {

    @Id
    private Long id;

    @Column("telegram_id")
    private Long telegramUserId;

    @Column("telegram_user_first_name")
    private String telegramUserFirstName;

    @Column("question_number")
    private Integer questionNumber;

    private String answer;

    @Column("time_of_answer")
    private LocalDateTime timeOfAnswer;

}
