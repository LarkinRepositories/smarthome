package ru.innopolis.sms.service;

import com.twilio.Twilio;
import com.twilio.exception.TwilioException;
import com.twilio.type.PhoneNumber;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.stereotype.Service;
import ru.innopolis.sms.pojo.SMS;

/**
 * будет отправлять SMS-сообщения, вызывая twilio API
 * @author Alina
 */
@Service
public class SMSServiceTwilio implements  SMSService {
    public static final String ACCOUNT_SID = "AC1a288ab3041952604727352e3aXXXXXX";
    public static final String AUTH_TOKEN = "b6621050b638c303447f887648YYYYYY";
    private static final String FROM_NUMBER = "+18502702772";//Номер телефона Twilio

    @Override
    public void sendSMS(SMS sms) {
        try {
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            Message message = Message.creator(
                    new PhoneNumber(sms.getTo()),//Номер телефона, на который вы отправляете сообщение
                    new PhoneNumber(FROM_NUMBER),
                    sms.getMessage())
                    .create();
            System.out.println("here is my id:" + message.getSid());// Уникальный идентификатор ресурса, созданный для управления этой транзакцией
        }catch (TwilioException e) {
            System.out.println(e.getMessage());
        }
    }
}

