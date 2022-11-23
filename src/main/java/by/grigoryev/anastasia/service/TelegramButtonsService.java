package by.grigoryev.anastasia.service;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public interface TelegramButtonsService {

    InlineKeyboardMarkup addMainButtons();

    InlineKeyboardMarkup newYearTestFirstButtons();

    InlineKeyboardMarkup newYearTestSecondButtons(String key);

    InlineKeyboardMarkup newYearTestThirdButtons();

    InlineKeyboardMarkup newYearTestFourthButtons();

}
