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

    private final List<String> keys = new ArrayList<>();

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

        buttons.put("1/1", "1️⃣  Да");
        buttons.put("1/2", "2️⃣  Скорее, да");
        buttons.put("1/3", "3️⃣  Не знаю");
        buttons.put("1/4", "4️⃣  Скорее, нет");
        buttons.put("1/5", "5️⃣  Нет");
        buttons.put("1/next", NEXT_QUESTION);

        createButtons(buttons, buttonList);

        return buildInlineKeyboardMarkup(buttonList);
    }

    @Override
    public InlineKeyboardMarkup newYearTestSecondButtons(String key) {
        Map<String, String> buttons = new LinkedHashMap<>();
        List<InlineKeyboardButton> buttonList = new ArrayList<>();

        keys.add(key);

        buttons.put("2/1", "1️⃣  Они сближают сотрудников");
        buttons.put("2/2", "2️⃣  Улучшают их коммуникацию в дальнейшем");
        buttons.put("2/3", "3️⃣  Повышают лояльность сотрудников к компании");
        buttons.put("2/4", "4️⃣  Позволяют чувствовать себя единой командой");
        buttons.put("2/5", "5️⃣  Позволяют пообщаться в неформальной обстановке");
        buttons.put("2/6", "6️⃣  Это награда от компании за хорошую работу");
        buttons.put("2/7", "7️⃣  Неформально подвести итоги года");
        buttons.put("2/8", "8️⃣  Чисто побухать и оттянуться по полной на халяву");
        buttons.put("2/9", "9️⃣  Сотрудникам не нужны Новогодние корпоративы");
        buttons.put("2/next", NEXT_QUESTION);
        buttons.put("2/previous", PREVIOUS_QUESTION);

        keys.forEach(buttons::remove);

        createButtons(buttons, buttonList);

        return buildInlineKeyboardMarkup(buttonList);
    }

    @Override
    public InlineKeyboardMarkup newYearTestThirdButtons() {
        Map<String, String> buttons = new LinkedHashMap<>();
        List<InlineKeyboardButton> buttonList = new ArrayList<>();

        buttons.put("3/1", "1️⃣  Да");
        buttons.put("3/2", "2️⃣  Скорее, да");
        buttons.put("3/3", "3️⃣  Не знаю");
        buttons.put("3/4", "4️⃣  Скорее, нет");
        buttons.put("3/5", "5️⃣  Нет");
        buttons.put("3/next", NEXT_QUESTION);
        buttons.put("3/previous", PREVIOUS_QUESTION);

        createButtons(buttons, buttonList);

        return buildInlineKeyboardMarkup(buttonList);
    }

    @Override
    public InlineKeyboardMarkup newYearTestFourthButtons() {
        Map<String, String> buttons = new LinkedHashMap<>();
        List<InlineKeyboardButton> buttonList = new ArrayList<>();

        buttons.put("4/1", "1️⃣  Мне всё не нравится на корпоративах");
        buttons.put("4/2", "2️⃣  Не нравится сдавать деньги на корпоративы");
        buttons.put("4/3", "3️⃣  Не нравится принимать участие в конкурсах");
        buttons.put("4/4", "4️⃣  Не люблю говорить тосты");
        buttons.put("4/5", "5️⃣  Не трезвые коллеги");
        buttons.put("4/6", "6️⃣  Невозможно как следует расслабиться");
        buttons.put("4/7", "7️⃣  Невозможность прийти со второй половинкой");
        buttons.put("4/8", "8️⃣  Беспокойство о том, как я выгляжу и что надеть");
        buttons.put("4/9", "9️⃣  Мне всегда скучно на таких праздниках");
        buttons.put("4/10", "1️⃣0️⃣  Отсутствие возможности отказаться от участия в корпоративе");
        buttons.put("4/11", "1️⃣1️⃣  Слишком много людей");
        buttons.put("4/12", "1️⃣2️⃣  Нетрезвый начальник");
        buttons.put("4/13", "1️⃣3️⃣  Недвусмысленные приставания коллег");
        buttons.put("4/14", "1️⃣4️⃣  Другое");
        buttons.put("4/previous", PREVIOUS_QUESTION);

        createButtons(buttons, buttonList);

        return buildInlineKeyboardMarkup(buttonList);
    }

    private static void createButtons(Map<String, String> buttons, List<InlineKeyboardButton> buttonList) {
        buttons.forEach((k, v) -> {
            InlineKeyboardButton button = InlineKeyboardButton.builder()
                    .text(v)
                    .callbackData(k)
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
