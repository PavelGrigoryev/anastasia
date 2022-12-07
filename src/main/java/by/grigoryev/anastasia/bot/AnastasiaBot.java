package by.grigoryev.anastasia.bot;

import by.grigoryev.anastasia.configuration.TestQuestions;
import by.grigoryev.anastasia.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
@Component
@RequiredArgsConstructor
public class AnastasiaBot extends TelegramLongPollingBot {

    public static final String EMOJI = "\uD83E\uDD73 ";
    @Value("${bot.name}")
    private String botName;

    @Value("${bot.token}")
    private String botToken;

    @Value("${yandex.download.link.url}")
    private String downloadLink;

    private final TestQuestions testQuestions;

    private final TelegramButtonsService telegramButtonsService;

    private final TelegramUserService telegramUserService;

    private final TelegramAnswerService telegramAnswerService;

    private final ExcelService excelService;

    private final YandexDiskService yandexDiskService;

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasCallbackQuery()) {
            handleCallback(update.getCallbackQuery());
        } else if (update.hasMessage()) {
            handleMessage(update.getMessage());
        }
    }

    private void handleMessage(Message message) {
        User user = message.getFrom();
        String text = message.getText();

        log.warn(user.getFirstName() + " wrote: " + text);

        if (message.hasText()) {

            if ("/menu".equals(text) || "/start".equals(text)) {
                sendMenu(user.getId(), "\uD83C\uDF89 Главное меню! Пользователь :  " + user.getFirstName());
            } else {
                sendText(user.getId(), "\uD83C\uDF89 Приветствую вас, " + user.getFirstName()
                        + "!\nДоступно пока только меню :\n/menu");
            }
        } else {
            sendText(user.getId(), "\uD83C\uDF89 Извините, доступны только текстовые команды...");
        }
    }

    private void handleCallback(CallbackQuery callbackQuery) {
        User user = callbackQuery.getFrom();
        String action = callbackQuery.getData();

        log.warn("{} action: {}", user.getFirstName(), action);

        switch (action) {
            case "newYear" -> {
                telegramUserService.save(user);
                sendEditMessage(callbackQuery, telegramButtonsService.newYearTestFirstButtons(),
                        EMOJI + testQuestions.getQuestion1() + " \uD83C\uDF89");
            }

            case "next" -> sendEditMessage(callbackQuery, telegramButtonsService.newYearTestThirdButtons(),
                    EMOJI + testQuestions.getQuestion3() + " \uD83C\uDF89");

            case "1/1", "1/2", "1/3", "1/4", "1/5", "2/1", "2/2", "2/3", "2/4", "2/5", "2/6", "2/7", "2/8", "2/9" -> {
                sendEditMessage(callbackQuery, telegramButtonsService.newYearTestSecondButtons(action),
                        EMOJI + testQuestions.getQuestion2() + " \uD83E\uDD73" +
                                "\n⚠ Можно выбрать несколько вариантов! \uD83C\uDF89");
                telegramAnswerService.save(user, action);
            }

            case "3/1", "3/2", "3/3", "3/4", "3/5", "4/1", "4/2", "4/3", "4/4", "4/5", "4/6", "4/7", "4/8", "4/9",
                    "4/10", "4/11", "4/12", "4/13", "4/14" -> {
                sendEditMessage(callbackQuery, telegramButtonsService.newYearTestFourthButtons(action),
                        EMOJI + testQuestions.getQuestion4() + " \uD83E\uDD73" +
                                "\n⚠ Можно выбрать несколько вариантов! \uD83C\uDF89");
                telegramAnswerService.save(user, action);
            }

            case "end" -> {
                telegramButtonsService.clearKeys();
                sendEditMessage(callbackQuery, telegramButtonsService.addMainButtons(),
                        "\uD83C\uDF89 Главное меню! Пользователь :  " + user.getFirstName());
                excelService.createSheet();
                yandexDiskService.uploadByTheReceivedLink();
                sendText(user.getId(), "Ссылка для скачивания :\n" + downloadLink);
            }

            default -> sendText(user.getId(), "\uD83C\uDF89 Приветствую вас, " + user.getFirstName()
                    + "!\nДоступно пока только меню :\n/menu");
        }

        closeCallbackQuery(callbackQuery);
    }

    private void closeCallbackQuery(CallbackQuery callbackQuery) {
        AnswerCallbackQuery close = AnswerCallbackQuery.builder()
                .callbackQueryId(callbackQuery.getId())
                .build();

        try {
            execute(close);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void sendEditMessage(CallbackQuery callbackQuery, InlineKeyboardMarkup inlineKeyboardMarkup, String text) {
        try {
            execute(EditMessageText.builder()
                    .chatId(callbackQuery.getFrom().getId())
                    .messageId(callbackQuery.getMessage().getMessageId())
                    .text(text)
                    .replyMarkup(inlineKeyboardMarkup)
                    .build());
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void sendText(Long who, String what) {
        SendMessage sendMessage = SendMessage.builder()
                .chatId(who)
                .text(what)
                .build();

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void sendMenu(Long who, String what) {
        SendMessage sendMessage = SendMessage.builder()
                .chatId(who)
                .text(what)
                .replyMarkup(telegramButtonsService.addMainButtons())
                .build();

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

}
