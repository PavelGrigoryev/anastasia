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

        buttons.put("1️⃣  Да", "1. Да");
        buttons.put("2️⃣  Скорее, да", "2. Скорее, да");
        buttons.put("3️⃣  Не знаю", "3. Не знаю");
        buttons.put("4️⃣  Скорее, нет", "4. Скорее, нет");
        buttons.put("5️⃣  Нет", "5. Нет");
        buttons.put(NEXT_QUESTION, "1/next");

        createButtons(buttons, buttonList);

        return buildInlineKeyboardMarkup(buttonList);
    }

    @Override
    public InlineKeyboardMarkup newYearTestSecondButtons() {
        Map<String, String> buttons = new LinkedHashMap<>();
        List<InlineKeyboardButton> buttonList = new ArrayList<>();

        buttons.put("1️⃣  Они сближают сотрудников", "2/1");
        buttons.put("2️⃣  Улучшают их коммуникацию в дальнейшем", "2/2");
        buttons.put("3️⃣  Повышают лояльность сотрудников к компании", "2/3");
        buttons.put("4️⃣  Позволяют чувствовать себя единой командой", "2/4");
        buttons.put("5️⃣  Позволяют пообщаться в неформальной обстановке", "2/5");
        buttons.put("6️⃣  Это награда от компании за хорошую работу", "2/6");
        buttons.put("7️⃣  Неформально подвести итоги года", "2/7");
        buttons.put("8️⃣  Чисто побухать и оттянуться по полной на халяву", "2/8");
        buttons.put("9️⃣  Сотрудникам не нужны Новогодние корпоративы", "2/9");
        buttons.put(NEXT_QUESTION, "2/next");
        buttons.put(PREVIOUS_QUESTION, "2/previous");

        createButtons(buttons, buttonList);

        return buildInlineKeyboardMarkup(buttonList);
    }

    @Override
    public InlineKeyboardMarkup newYearTestThirdButtons() {
        Map<String, String> buttons = new LinkedHashMap<>();
        List<InlineKeyboardButton> buttonList = new ArrayList<>();

        buttons.put("1️⃣  Да", "3/1");
        buttons.put("2️⃣  Скорее, да", "3/2");
        buttons.put("3️⃣  Не знаю", "3/3");
        buttons.put("4️⃣  Скорее, нет", "3/4");
        buttons.put("5️⃣  Нет", "3/5");
        buttons.put(NEXT_QUESTION, "3/next");
        buttons.put(PREVIOUS_QUESTION, "3/previous");

        createButtons(buttons, buttonList);

        return buildInlineKeyboardMarkup(buttonList);
    }

    @Override
    public InlineKeyboardMarkup newYearTestFourthButtons() {
        Map<String, String> buttons = new LinkedHashMap<>();
        List<InlineKeyboardButton> buttonList = new ArrayList<>();

        buttons.put("1️⃣  Мне всё не нравится на корпоративах", "4/1");
        buttons.put("2️⃣  Не нравится сдавать деньги на корпоративы", "4/2");
        buttons.put("3️⃣  Не нравится принимать участие в конкурсах", "4/3");
        buttons.put("4️⃣  Не люблю говорить тосты", "4/4");
        buttons.put("5️⃣  Не трезвые коллеги", "4/5");
        buttons.put("6️⃣  Невозможно как следует расслабиться", "4/6");
        buttons.put("7️⃣  Невозможность прийти со второй половинкой", "4/7");
        buttons.put("8️⃣  Беспокойство о том, как я выгляжу и что надеть", "4/8");
        buttons.put("9️⃣  Мне всегда скучно на таких праздниках", "4/9");
        buttons.put("1️⃣0️⃣  Отсутствие возможности отказаться от участия в корпоративе", "4/10");
        buttons.put("1️⃣1️⃣  Слишком много людей", "4/11");
        buttons.put("1️⃣2️⃣  Нетрезвый начальник", "4/12");
        buttons.put("1️⃣3️⃣  Недвусмысленные приставания коллег", "4/13");
        buttons.put("1️⃣4️⃣  Другое", "4/14");
        buttons.put(PREVIOUS_QUESTION, "4/previous");

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
