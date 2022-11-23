package by.grigoryev.anastasia.bot;

import by.grigoryev.anastasia.service.TelegramButtonsService;
import by.grigoryev.anastasia.service.TelegramUserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
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

@Slf4j
@Component
@RequiredArgsConstructor
public class AnastasiaBot extends TelegramLongPollingBot {

    @Value("${bot.name}")
    private String botName;

    @Value("${bot.token}")
    private String botToken;

    private final TelegramButtonsService telegramButtonsService;

    private final TelegramUserService telegramUserService;

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
                sendMenu(user.getId(), "Главное меню! Пользователь : " + user.getFirstName());
            } else {
                sendText(user.getId(), "Приветствую вас, " + user.getFirstName()
                        + "!\nДоступно пока только меню :\n/menu");
            }
        } else {
            sendText(user.getId(), "Извините, доступны только текстовые команды...");
        }
    }

    @SneakyThrows
    private void handleCallback(CallbackQuery callbackQuery) {
        User user = callbackQuery.getFrom();
        String action = callbackQuery.getData();
        log.warn("{} action: {}", user.getFirstName(), action);

        switch (action) {
            case "newYear" ->
                    addEditMessage(callbackQuery, telegramButtonsService.newYearTestFirstButtons(),
                            "Как вы считаете, сотрудникам компаний нужны Новогодние корпоративы?");

            case "next" ->
                    addEditMessage(callbackQuery, telegramButtonsService.newYearTestThirdButtons(),
                            "Будет ли в этом году в вашей компании празднование Нового года?");

            case "1/1", "1/2", "1/3", "1/4", "1/5", "2/1", "2/2", "2/3", "2/4", "2/5", "2/6", "2/7", "2/8", "2/9" ->
                    telegramUserService.save(callbackQuery, action).subscribe(newYearTest ->
                            addEditMessage(callbackQuery, telegramButtonsService.newYearTestSecondButtons(action),
                                    "Для чего сотрудникам компаний нужны Новогодние корпоративы?" +
                                            "\n⚠ Можно выбрать несколько вариантов!"));

            case "3/1", "3/2", "3/3", "3/4", "3/5", "4/1", "4/2", "4/3", "4/4", "4/5", "4/6", "4/7", "4/8", "4/9",
                    "4/10", "4/11", "4/12", "4/13", "4/14" ->
                    telegramUserService.save(callbackQuery, action).subscribe(newYearTest ->
                            addEditMessage(callbackQuery, telegramButtonsService.newYearTestFourthButtons(action),
                                    "Что вам больше всего не нравится на новогодних корпоративах?" +
                                            "\n⚠ Можно выбрать несколько вариантов!"));

            case "end" -> {
                telegramButtonsService.clearKeys();
                addEditMessage(callbackQuery, telegramButtonsService.addMainButtons(),
                        "Главное меню! Пользователь : " + user.getFirstName());
            }

            default -> sendText(user.getId(), "Приветствую вас, " + user.getFirstName()
                    + "!\nДоступно пока только меню :\n/menu");
        }

        AnswerCallbackQuery close = AnswerCallbackQuery.builder()
                .callbackQueryId(callbackQuery.getId())
                .build();

        execute(close);
    }

    @SneakyThrows
    private void addEditMessage(CallbackQuery callbackQuery, InlineKeyboardMarkup inlineKeyboardMarkup, String text) {

        execute(EditMessageText.builder()
                .chatId(callbackQuery.getFrom().getId())
                .messageId(callbackQuery.getMessage().getMessageId())
                .text(text)
                .replyMarkup(inlineKeyboardMarkup)
                .build());
    }

    @SneakyThrows
    public void sendText(Long who, String what) {
        SendMessage sendMessage = SendMessage.builder()
                .chatId(who.toString())
                .text(what)
                .build();

        execute(sendMessage);
    }

    @SneakyThrows
    public void sendMenu(Long who, String what) {
        SendMessage sendMessage = SendMessage.builder()
                .chatId(who.toString())
                .text(what)
                .replyMarkup(telegramButtonsService.addMainButtons())
                .build();

        execute(sendMessage);
    }

}
