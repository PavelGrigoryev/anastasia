package by.grigoryev.anastasia.configuration;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class TestAnswers {

    private String yes = "Да";
    private String probablyYes = "Скорее, да";
    private String dontKnow = "Не знаю";
    private String probablyNO = "Скорее, нет";
    private String not = "Нет";

    private String answer2n1 = "Они сближают сотрудников";
    private String answer2n2 = "Улучшают их коммуникацию в дальнейшем";
    private String answer2n3 = "Повышают лояльность сотрудников к компании";
    private String answer2n4 = "Позволяют чувствовать себя единой командой";
    private String answer2n5 = "Позволяют пообщаться в неформальной обстановке";
    private String answer2n6 = "Это награда от компании за хорошую работу";
    private String answer2n7 = "Неформально подвести итоги года";
    private String answer2n8 = "Чисто побухать и оттянуться по полной на халяву";
    private String answer2n9 = "Сотрудникам не нужны Новогодние корпоративы";

    private String answer4n1 = "Мне всё не нравится на корпоративах";
    private String answer4n2 = "Не нравится сдавать деньги на корпоративы";
    private String answer4n3 = "Не нравится принимать участие в конкурсах";
    private String answer4n4 = "Не люблю говорить тосты";
    private String answer4n5 = "Не трезвые коллеги";
    private String answer4n6 = "Невозможно как следует расслабиться";
    private String answer4n7 = "Невозможность прийти со второй половинкой";
    private String answer4n8 = "Беспокойство о том, как я выгляжу и что надеть";
    private String answer4n9 = "Мне всегда скучно на таких праздниках";
    private String answer4n10 = "Отсутствие возможности отказаться от участия в корпоративе";
    private String answer4n11 = "Слишком много людей";
    private String answer4n12 = "Нетрезвый начальник";
    private String answer4n13 = "Недвусмысленные приставания коллег";
    private String answer4n14 = "Другое";

}
