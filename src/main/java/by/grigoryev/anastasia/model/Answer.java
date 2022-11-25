package by.grigoryev.anastasia.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "telegram_answers")
public class Answer {

    private String id;

    private String firstName;

    private String message;

    private LocalDateTime testStartTime;

    private Long telegramUserId;

}
