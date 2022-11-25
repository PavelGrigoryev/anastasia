package by.grigoryev.anastasia.bot;

import by.grigoryev.anastasia.service.AnswerService;
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

import java.util.ArrayList;
import java.util.List;

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

    private final AnswerService answerService;

    private final List<String> results = new ArrayList<>();

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

    @SneakyThrows
    private void handleCallback(CallbackQuery callbackQuery) {
        User user = callbackQuery.getFrom();
        String action = callbackQuery.getData();
        log.warn("{} action: {}", user.getFirstName(), action);

        switch (action) {
            case "newYear" -> telegramUserService.save(callbackQuery).subscribe(telegramUser ->
                    addEditMessage(callbackQuery, telegramButtonsService.newYearTestFirstButtons(),
                            "\uD83E\uDD73 Как вы считаете, сотрудникам компаний нужны Новогодние корпоративы? \uD83C\uDF89"));

            case "next" -> addEditMessage(callbackQuery, telegramButtonsService.newYearTestThirdButtons(),
                    "\uD83E\uDD73 Будет ли в этом году в вашей компании празднование Нового года? \uD83C\uDF89");

            case "1/1", "1/2", "1/3", "1/4", "1/5", "2/1", "2/2", "2/3", "2/4", "2/5", "2/6", "2/7", "2/8", "2/9" -> {
                results.add(action);
                answerService.save(callbackQuery, action).subscribe(answer ->
                        addEditMessage(callbackQuery, telegramButtonsService.newYearTestSecondButtons(action),
                                "\uD83E\uDD73 Для чего сотрудникам компаний нужны Новогодние корпоративы? \uD83E\uDD73" +
                                        "\n⚠ Можно выбрать несколько вариантов! \uD83C\uDF89"));
            }

            case "3/1", "3/2", "3/3", "3/4", "3/5", "4/1", "4/2", "4/3", "4/4", "4/5", "4/6", "4/7", "4/8", "4/9",
                    "4/10", "4/11", "4/12", "4/13", "4/14" -> {
                results.add(action);
                answerService.save(callbackQuery, action).subscribe(answer ->
                        addEditMessage(callbackQuery, telegramButtonsService.newYearTestFourthButtons(action),
                                "\uD83E\uDD73 Что вам больше всего не нравится на новогодних корпоративах? \uD83E\uDD73" +
                                        "\n⚠ Можно выбрать несколько вариантов! \uD83C\uDF89"));
            }

            case "end" -> {
                telegramButtonsService.clearKeys();
                addEditMessage(callbackQuery, telegramButtonsService.addMainButtons(),
                        "\uD83C\uDF89 Главное меню! Пользователь :  " + user.getFirstName());
                StringBuilder resultsBuilder = new StringBuilder();
                String question = " на вопрос № ";
                String answer = " выбрал ответ № ";
                results.forEach(s -> {
                    if (s.startsWith("1")) {
                        resultsBuilder.append(user.getFirstName())
                                .append(question)
                                .append(s.charAt(0))
                                .append(answer)
                                .append(s.substring(2));
                    } else if (s.startsWith("2")) {
                        resultsBuilder.append("\n")
                                .append(user.getFirstName())
                                .append(question)
                                .append(s.charAt(0))
                                .append(answer)
                                .append(s.substring(2));
                    } else if (s.startsWith("3")) {
                        resultsBuilder.append("\n")
                                .append(user.getFirstName())
                                .append(question)
                                .append(s.charAt(0))
                                .append(answer)
                                .append(s.substring(2));
                    } else if (s.startsWith("4")) {
                        resultsBuilder.append("\n")
                                .append(user.getFirstName())
                                .append(question)
                                .append(s.charAt(0))
                                .append(answer)
                                .append(s.substring(2));
                    }
                });
                sendText(user.getId(), resultsBuilder.toString());
                results.clear();
            }

            default -> sendText(user.getId(), "\uD83C\uDF89 Приветствую вас, " + user.getFirstName()
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
    private void sendText(Long who, String what) {
        SendMessage sendMessage = SendMessage.builder()
                .chatId(who.toString())
                .text(what)
                .build();

        execute(sendMessage);
    }

    @SneakyThrows
    private void sendMenu(Long who, String what) {
        SendMessage sendMessage = SendMessage.builder()
                .chatId(who.toString())
                .text(what)
                .replyMarkup(telegramButtonsService.addMainButtons())
                .build();

        execute(sendMessage);
    }

}
