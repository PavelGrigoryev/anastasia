package by.grigoryev.anastasia.service.impl;

import by.grigoryev.anastasia.service.TelegramButtonsService;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

@Service
public class TelegramButtonsServiceImpl implements TelegramButtonsService {

    @Override
    public InlineKeyboardMarkup addMainButtons() {

        InlineKeyboardButton main = InlineKeyboardButton.builder()
                .text("Главная страница")
                .callbackData("main")
                .url("https://icra.capital-space.com/")
                .build();

        InlineKeyboardButton register = InlineKeyboardButton.builder()
                .text("Регистрация")
                .callbackData("register")
                .url("https://icra.capital-space.com/register/")
                .build();

        InlineKeyboardButton enter = InlineKeyboardButton.builder()
                .text("Вход")
                .callbackData("enter")
                .url("https://icra.capital-space.com/login/")
                .build();

        InlineKeyboardButton quiz = InlineKeyboardButton.builder()
                .text("Викторина")
                .callbackData("quiz")
                .build();

        return InlineKeyboardMarkup.builder()
                .keyboardRow(List.of(main))
                .keyboardRow(List.of(register))
                .keyboardRow(List.of(enter))
                .keyboardRow(List.of(quiz))
                .build();
    }

    @Override
    public InlineKeyboardMarkup quizGameFirstButtons(String message) {

        InlineKeyboardButton first = InlineKeyboardButton.builder()
                .text(message)
                .callbackData("first")
                .build();

        InlineKeyboardButton second = InlineKeyboardButton.builder()
                .text("Джинджер")
                .callbackData("second")
                .build();

        InlineKeyboardButton third = InlineKeyboardButton.builder()
                .text("Себастьян")
                .callbackData("third")
                .build();

        return InlineKeyboardMarkup.builder()
                .keyboardRow(List.of(first, second, third))
                .build();
    }

    @Override
    public InlineKeyboardMarkup quizGameSecondButtons() {

        InlineKeyboardButton horse = InlineKeyboardButton.builder()
                .text("Лошадь")
                .callbackData("horse")
                .build();

        InlineKeyboardButton unicorn = InlineKeyboardButton.builder()
                .text("Единорог")
                .callbackData("unicorn")
                .build();

        InlineKeyboardButton wolf = InlineKeyboardButton.builder()
                .text("Волк")
                .callbackData("wolf")
                .build();

        return InlineKeyboardMarkup.builder()
                .keyboardRow(List.of(horse, unicorn, wolf))
                .build();
    }

    @Override
    public InlineKeyboardMarkup quizGameThirdButtons() {

        InlineKeyboardButton basilica = InlineKeyboardButton.builder()
                .text("Базилика")
                .callbackData("basilica")
                .build();

        InlineKeyboardButton lemon = InlineKeyboardButton.builder()
                .text("Лимона")
                .callbackData("lemon")
                .build();

        InlineKeyboardButton orange = InlineKeyboardButton.builder()
                .text("Апельсина")
                .callbackData("orange")
                .build();

        return InlineKeyboardMarkup.builder()
                .keyboardRow(List.of(basilica, lemon, orange))
                .build();
    }

}
