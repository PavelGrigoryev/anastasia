package by.grigoryev.anastasia.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "telegram_id",
        "user_name",
        "first_name",
        "last_name",
        "time_of_registration",
        "language_code"
})
public class TelegramUserDto {

    private Long id;

    @JsonProperty("telegram_id")
    private Long telegramUserId;

    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("time_of_registration")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timeOfRegistration;

    @JsonProperty("language_code")
    private String languageCode;

}
