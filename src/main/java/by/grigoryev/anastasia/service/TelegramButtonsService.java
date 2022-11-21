package by.grigoryev.anastasia.service;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public interface TelegramButtonsService {

    InlineKeyboardMarkup addMainButtons();

    InlineKeyboardMarkup quizGameFirstButtons(String message);

    InlineKeyboardMarkup quizGameSecondButtons();

    InlineKeyboardMarkup quizGameThirdButtons();

}
