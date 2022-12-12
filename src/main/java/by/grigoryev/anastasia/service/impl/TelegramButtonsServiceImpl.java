package by.grigoryev.anastasia.service.impl;

import by.grigoryev.anastasia.configuration.TestAnswers;
import by.grigoryev.anastasia.configuration.TestNumbers;
import by.grigoryev.anastasia.service.TelegramButtonsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TelegramButtonsServiceImpl implements TelegramButtonsService {

    @Value("${api.url}")
    private String url;

    private final TestAnswers testAnswers;

    private final TestNumbers testNumbers;

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

        buttons.put("1/1", testNumbers.getNumber1() + testAnswers.getYes());
        buttons.put("1/2", testNumbers.getNumber2() + testAnswers.getProbablyYes());
        buttons.put("1/3", testNumbers.getNumber3() + testAnswers.getDontKnow());
        buttons.put("1/4", testNumbers.getNumber4() + testAnswers.getProbablyNO());
        buttons.put("1/5", testNumbers.getNumber5() + testAnswers.getNot());

        createButtons(buttons, buttonList);

        return buildInlineKeyboardMarkup(buttonList);
    }

    @Override
    public InlineKeyboardMarkup newYearTestSecondButtons(String key) {
        Map<String, String> buttons = new LinkedHashMap<>();
        List<InlineKeyboardButton> buttonList = new ArrayList<>();

        buttons.put("2/1", testNumbers.getNumber1() + testAnswers.getAnswer2n1());
        buttons.put("2/2", testNumbers.getNumber2() + testAnswers.getAnswer2n2());
        buttons.put("2/3", testNumbers.getNumber3() + testAnswers.getAnswer2n3());
        buttons.put("2/4", testNumbers.getNumber4() + testAnswers.getAnswer2n4());
        buttons.put("2/5", testNumbers.getNumber5() + testAnswers.getAnswer2n5());
        buttons.put("2/6", testNumbers.getNumber6() + testAnswers.getAnswer2n6());
        buttons.put("2/7", testNumbers.getNumber7() + testAnswers.getAnswer2n7());
        buttons.put("2/8", testNumbers.getNumber8() + testAnswers.getAnswer2n8());
        buttons.put("2/9", testNumbers.getNumber9() + testAnswers.getAnswer2n9());

        if (key.startsWith("2")) {
            buttons.put("next", "\uD83D\uDEAB Перейти к следующему вопросу");
            keys.add(key);
        }

        keys.forEach(buttons::remove);

        createButtons(buttons, buttonList);

        return buildInlineKeyboardMarkup(buttonList);
    }

    @Override
    public InlineKeyboardMarkup newYearTestThirdButtons() {
        Map<String, String> buttons = new LinkedHashMap<>();
        List<InlineKeyboardButton> buttonList = new ArrayList<>();

        buttons.put("3/1", testNumbers.getNumber1() + testAnswers.getYes());
        buttons.put("3/2", testNumbers.getNumber2() + testAnswers.getProbablyYes());
        buttons.put("3/3", testNumbers.getNumber3() + testAnswers.getDontKnow());
        buttons.put("3/4", testNumbers.getNumber4() + testAnswers.getProbablyNO());
        buttons.put("3/5", testNumbers.getNumber5() + testAnswers.getNot());

        createButtons(buttons, buttonList);

        return buildInlineKeyboardMarkup(buttonList);
    }

    @Override
    public InlineKeyboardMarkup newYearTestFourthButtons(String key) {
        Map<String, String> buttons = new LinkedHashMap<>();
        List<InlineKeyboardButton> buttonList = new ArrayList<>();

        buttons.put("4/1", testNumbers.getNumber1() + testAnswers.getAnswer4n1());
        buttons.put("4/2", testNumbers.getNumber2() + testAnswers.getAnswer4n2());
        buttons.put("4/3", testNumbers.getNumber3() + testAnswers.getAnswer4n3());
        buttons.put("4/4", testNumbers.getNumber4() + testAnswers.getAnswer4n4());
        buttons.put("4/5", testNumbers.getNumber5() + testAnswers.getAnswer4n5());
        buttons.put("4/6", testNumbers.getNumber6() + testAnswers.getAnswer4n6());
        buttons.put("4/7", testNumbers.getNumber7() + testAnswers.getAnswer4n7());
        buttons.put("4/8", testNumbers.getNumber8() + testAnswers.getAnswer4n8());
        buttons.put("4/9", testNumbers.getNumber9() + testAnswers.getAnswer4n9());
        buttons.put("4/10", testNumbers.getNumber10() + testAnswers.getAnswer4n10());
        buttons.put("4/11", testNumbers.getNumber11() + testAnswers.getAnswer4n11());
        buttons.put("4/12", testNumbers.getNumber12() + testAnswers.getAnswer4n12());
        buttons.put("4/13", testNumbers.getNumber13() + testAnswers.getAnswer4n13());
        buttons.put("4/14", testNumbers.getNumber14() + testAnswers.getAnswer4n14());

        if (key.startsWith("4")) {
            buttons.put("end", "\uD83D\uDEAB Закончить тест и получить результаты!");
            keys.add(key);
        }

        keys.forEach(buttons::remove);

        createButtons(buttons, buttonList);

        return buildInlineKeyboardMarkup(buttonList);
    }

    @Override
    public void clearKeys() {
        keys.clear();
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
