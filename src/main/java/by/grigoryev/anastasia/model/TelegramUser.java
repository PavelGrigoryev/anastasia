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
@Document(collection = "telegram_users")
public class TelegramUser {

    private String id;

    private Long telegramId;

    private String userName;

    private String firstName;

    private String lastName;

    private LocalDateTime timeOfRegistration;

    private String languageCode;

}
