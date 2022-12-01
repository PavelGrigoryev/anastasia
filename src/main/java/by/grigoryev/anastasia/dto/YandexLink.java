package by.grigoryev.anastasia.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class YandexLink {

    @JsonProperty("href")
    private String href;

}
