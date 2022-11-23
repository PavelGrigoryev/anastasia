package by.grigoryev.anastasia.service.impl;

import by.grigoryev.anastasia.service.TelegramButtonsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class TelegramButtonsServiceImpl implements TelegramButtonsService {

    public static final String NEXT_QUESTION = "Следующий вопрос ➡";
    public static final String PREVIOUS_QUESTION = "Предыдущий вопрос ⬅";
    @Value("${api.url}")
    private String url;

    @Override
    public InlineKeyboardMarkup addMainButtons() {

        InlineKeyboardButton main = InlineKeyboardButton.builder()
                .text("Главная страница \uD83C\uDFE2")
                .url(url)
                .build();

        InlineKeyboardButton register = InlineKeyboardButton.builder()
                .text("Регистрация \uD83D\uDCBE")
                .url(url + "/register/")
                .build();

        InlineKeyboardButton enter = InlineKeyboardButton.builder()
                .text("Вход \uD83D\uDEAA")
                .url(url + "/login/")
                .build();

        InlineKeyboardButton newYear = InlineKeyboardButton.builder()
                .text("Новый год тест \uD83C\uDF84")
                .callbackData("newYear")
                .build();

        return InlineKeyboardMarkup.builder()
                .keyboardRow(List.of(main))
                .keyboardRow(List.of(register))
                .keyboardRow(List.of(enter))
                .keyboardRow(List.of(newYear))
                .build();
    }

    @Override
    public InlineKeyboardMarkup newYearTestFirstButtons() {
        Map<String, String> buttons = new LinkedHashMap<>();
        List<InlineKeyboardButton> buttonList = new ArrayList<>();

        buttons.put("1️⃣  Да", "firstAnswer1");
        buttons.put("2️⃣  Скорее, да", "secondAnswer1");
        buttons.put("3️⃣  Не знаю", "thirdAnswer1");
        buttons.put("4️⃣  Скорее, нет", "fourthAnswer1");
        buttons.put("5️⃣  Нет", "fifthAnswer1");
        buttons.put(NEXT_QUESTION, "next1");

        createButtons(buttons, buttonList);

        return buildInlineKeyboardMarkup(buttonList);
    }

    @Override
    public InlineKeyboardMarkup newYearTestSecondButtons() {
        Map<String, String> buttons = new LinkedHashMap<>();
        List<InlineKeyboardButton> buttonList = new ArrayList<>();

        buttons.put("1️⃣  Они сближают сотрудников", "firstAnswer2");
        buttons.put("2️⃣  Улучшают их коммуникацию в дальнейшем", "secondAnswer2");
        buttons.put("3️⃣  Повышают лояльность сотрудников к компании", "thirdAnswer2");
        buttons.put("4️⃣  Позволяют чувствовать себя единой командой", "fourthAnswer2");
        buttons.put("5️⃣  Позволяют пообщаться в неформальной обстановке", "fifthAnswer2");
        buttons.put("6️⃣  Это награда от компании за хорошую работу", "sixthAnswer2");
        buttons.put("7️⃣  Неформально подвести итоги года", "sevensAnswer2");
        buttons.put("8️⃣  Чисто побухать и оттянуться по полной на халяву", "eightsAnswer2");
        buttons.put("9️⃣  Сотрудникам не нужны Новогодние корпоративы", "ninesAnswer2");
        buttons.put(NEXT_QUESTION, "next2");
        buttons.put(PREVIOUS_QUESTION, "previous2");

        createButtons(buttons, buttonList);

        return buildInlineKeyboardMarkup(buttonList);
    }

    @Override
    public InlineKeyboardMarkup newYearTestThirdButtons() {
        Map<String, String> buttons = new LinkedHashMap<>();
        List<InlineKeyboardButton> buttonList = new ArrayList<>();

        buttons.put("1️⃣  Да", "firstAnswer3");
        buttons.put("2️⃣  Скорее, да", "secondAnswer3");
        buttons.put("3️⃣  Не знаю", "thirdAnswer3");
        buttons.put("4️⃣  Скорее, нет", "fourthAnswer3");
        buttons.put("5️⃣  Нет", "fifthAnswer3");
        buttons.put(NEXT_QUESTION, "next3");
        buttons.put(PREVIOUS_QUESTION, "previous3");

        createButtons(buttons, buttonList);

        return buildInlineKeyboardMarkup(buttonList);
    }

    @Override
    public InlineKeyboardMarkup newYearTestFourthButtons() {
        Map<String, String> buttons = new LinkedHashMap<>();
        List<InlineKeyboardButton> buttonList = new ArrayList<>();

        buttons.put("1️⃣  Мне всё не нравится на корпоративах", "firstAnswer4");
        buttons.put("2️⃣  Не нравится сдавать деньги на корпоративы", "secondAnswer4");
        buttons.put("3️⃣  Не нравится принимать участие в конкурсах", "thirdAnswer4");
        buttons.put("4️⃣  Не люблю говорить тосты", "fourthAnswer4");
        buttons.put("5️⃣  Не трезвые коллеги", "fifthAnswer4");
        buttons.put("6️⃣  Невозможно как следует расслабиться", "sixthAnswer4");
        buttons.put("7️⃣  Невозможность прийти со второй половинкой", "seventhAnswer4");
        buttons.put("8️⃣  Беспокойство о том, как я выгляжу и что надеть", "eightsAnswer4");
        buttons.put("9️⃣  Мне всегда скучно на таких праздниках", "ninthAnswer4");
        buttons.put("1️⃣0️⃣  Отсутствие возможности отказаться от участия в корпоративе", "tenthAnswer4");
        buttons.put("1️⃣1️⃣  Слишком много людей", "eleventhAnswer4");
        buttons.put("1️⃣2️⃣  Нетрезвый начальник", "twelfthAnswer4");
        buttons.put("1️⃣3️⃣  Недвусмысленные приставания коллег", "thirteenthAnswer4");
        buttons.put("1️⃣4️⃣  Другое", "fourteenthAnswer4");
        buttons.put(PREVIOUS_QUESTION, "previous4");

        createButtons(buttons, buttonList);

        return buildInlineKeyboardMarkup(buttonList);
    }

    private static void createButtons(Map<String, String> buttons, List<InlineKeyboardButton> buttonList) {
        buttons.forEach((s1, s2) -> {
            InlineKeyboardButton button = InlineKeyboardButton.builder()
                    .text(s1)
                    .callbackData(s2)
                    .build();
            buttonList.add(button);
        });
    }

    private static InlineKeyboardMarkup buildInlineKeyboardMarkup(List<InlineKeyboardButton> buttonList) {
        InlineKeyboardMarkup.InlineKeyboardMarkupBuilder builder = InlineKeyboardMarkup.builder();
        buttonList.forEach(inlineKeyboardButton -> builder.keyboardRow(List.of(inlineKeyboardButton)));
        return builder.build();
    }

}
