package by.grigoryev.anastasia.configuration;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class TestQuestions {

    private String question1 = "Как вы считаете, сотрудникам компаний нужны Новогодние корпоративы?";

    private String question2 = "Для чего сотрудникам компаний нужны Новогодние корпоративы?";

    private String question3 = "Будет ли в этом году в вашей компании празднование Нового года?";

    private String question4 = "Что вам больше всего не нравится на новогодних корпоративах?";

}
