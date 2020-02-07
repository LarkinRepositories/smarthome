package ru.innopolis.telegramService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Collections;

/**
 * Класс TelegramBot
 * <p>
 * 06.02.2020
 *
 * @author Александр Коваленко
 */
@Component
public class TelegramBot extends TelegramLongPollingBot {
    private static final Logger log = LoggerFactory.getLogger(TelegramBot.class);


    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            sendNotificationToChatId(update.getMessage().getText());
        }

        if (update.hasMessage() && update.getMessage().hasText() && update.getMessage().getText().equals("/start")) {
            Long chatId = update.getMessage().getChatId();

            SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
                    .setChatId(chatId)
                    .setText("номер телефона?");
            setKeyboardForRequestContact(message);

            try {
                execute(message); // Call method to send the message

            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        // Check that update has a message and the message has contact
        if (update.hasMessage() && update.getMessage().hasContact()) {
            Message message = update.getMessage();
            Contact contact = message.getContact();

            if (!contact.getUserID().equals(message.getFrom().getId())) {
                //todo несовпадение контактов, надо прервать обработку
            }

            String phoneNumber = contact.getPhoneNumber();
            log.info("номер телефона " + phoneNumber + message.getChatId());
            log.info(update.getMessage().getContact().toString());

            //todo проверить номер телефона в базе, сохранить chat id в пользователя authService


        }
    }

    /**
     * Произвольное notification в чат id
     *
     * @param notify
     */
    public void sendNotificationToChatId(String notify) {
        SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
                .setChatId(524594292l)
                .setText(notify);
        try {
            execute(message); // Call method to send the message

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }


    }

    @Override
    public String getBotUsername() {
        return "PrettySmartHomeBot";
    }

    @Override
    public String getBotToken() {
        System.getProperty("SMART_HOME_BOT");
        return "983551610:AAHC3sWPcDVqEC0VQpWvlAY3-13J3KzM_Qs";
    }

    public void setKeyboardForRequestContact(SendMessage message) {
        KeyboardButton kb = new KeyboardButton().setText("Вот он!");
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);

        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add(new KeyboardButton().setText("Пожалуйста").setRequestContact(true));
        replyKeyboardMarkup.setKeyboard(Collections.singletonList(keyboardRow));
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        message.setReplyMarkup(replyKeyboardMarkup);
    }
}
