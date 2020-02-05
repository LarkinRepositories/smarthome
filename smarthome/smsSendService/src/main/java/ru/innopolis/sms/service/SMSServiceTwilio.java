package ru.innopolis.sms.service;

import com.twilio.Twilio;
import com.twilio.exception.TwilioException;
import com.twilio.type.PhoneNumber;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import ru.innopolis.sms.pojo.SMS;

/**
 * будет отправлять SMS-сообщения, вызывая twilio API
 * @author Alina
 */
@Service
public class SMSServiceTwilio implements  SMSService {
    @Value("#{systemEnvironment['TWILIO_ACCOUNT_SID']}")
    private String ACCOUNT_SID;

    @Value("#{systemEnvironment['TWILIO_AUTH_TOKEN']}")
    private String AUTH_TOKEN;

    @Value("#{systemEnvironment['TWILIO_PHONE_NUMBER']}")
    private String FROM_NUMBER;//Номер телефона Twilio

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

