package ru.innopolis.sms.service;

import ru.innopolis.sms.pojo.SMS;

public interface SMSService {
    void sendSMS(SMS sms);
}
