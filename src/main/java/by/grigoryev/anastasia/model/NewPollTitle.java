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
@Table(name = "new_poll_titles")
public class NewPollTitle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(name = "telegram_id")
    private Long telegramId;

    @Column(name = "telegram_user_id")
    private Long telegramUserId;

    @Column(name = "time_of_creation")
    private LocalDateTime timeOfCreation;

}
