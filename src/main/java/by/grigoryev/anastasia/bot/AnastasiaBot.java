package by.grigoryev.anastasia.bot;

import by.grigoryev.anastasia.service.NewYearTestService;
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
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
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

    private final NewYearTestService newYearTestService;

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
            case "newYear" -> telegramUserService.save(callbackQuery).subscribe(telegramUserDto ->
                    addEditMessage(callbackQuery, telegramButtonsService.newYearTestFirstButtons(),
                            "Как вы считаете, сотрудникам компаний нужны Новогодние корпоративы?"));

            case "1/next", "3/previous" ->
                    addEditMessage(callbackQuery, telegramButtonsService.newYearTestSecondButtons(),
                            "Для чего сотрудникам компаний нужны Новогодние корпоративы?");

            case "2/next", "4/previous" ->
                    addEditMessage(callbackQuery, telegramButtonsService.newYearTestThirdButtons(),
                            "Будет ли в этом году в вашей компании празднование Нового года?");

            case "3/next" -> addEditMessage(callbackQuery, telegramButtonsService.newYearTestFourthButtons(),
                    "Что вам больше всего не нравится на новогодних корпоративах?");

            case "2/previous" -> addEditMessage(callbackQuery, telegramButtonsService.newYearTestFirstButtons(),
                    "Как вы считаете, сотрудникам компаний нужны Новогодние корпоративы?");

            case "1. Да", "2. Скорее, да", "3. Не знаю", "4. Скорее, нет", "5. Нет" ->
                    newYearTestService.save(callbackQuery, 1, action).subscribe(newYearTest ->
                            addEditMessage(callbackQuery, telegramButtonsService.newYearTestSecondButtons(),
                                    "Для чего сотрудникам компаний нужны Новогодние корпоративы?"));

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
                .build());

        execute(EditMessageReplyMarkup.builder()
                .chatId(callbackQuery.getFrom().getId())
                .messageId(callbackQuery.getMessage().getMessageId())
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
