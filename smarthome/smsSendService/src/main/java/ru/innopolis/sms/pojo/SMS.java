package ru.innopolis.sms.pojo;

/**
 * Класс СМС
 * @author Alina
 */
public class SMS {
    /**
     * Номер телефона, куда отправляется сообщение
     */
    private String to;
    /**
     * Текст сообщения для отправки
     */
    private String message;

    public SMS(String to, String message) {
        this.to = to;
        this.message = message;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "SMS{" +
                "to='" + to + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
