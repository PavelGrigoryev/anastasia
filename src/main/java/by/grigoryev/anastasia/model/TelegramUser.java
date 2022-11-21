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
@Table("telegram_users")
public class TelegramUser {

    @Id
    private Long id;

    @Column("telegram_id")
    private Long telegramUserId;

    @Column("user_name")
    private String userName;

    @Column("first_name")
    private String firstName;

    @Column("last_name")
    private String lastName;

    @Column("time_of_registration")
    private LocalDateTime timeOfRegistration;

    @Column("language_code")
    private String languageCode;

}
