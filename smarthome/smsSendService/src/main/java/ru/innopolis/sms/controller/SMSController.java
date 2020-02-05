package ru.innopolis.sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.innopolis.sms.pojo.SMS;
import ru.innopolis.sms.service.SMSService;

/**
 * Будет принимать запрос на отправку SMS
 * @author Alina
 */
@RestController
public class SMSController {
    @Autowired
    private SMSService smsService;

    @RequestMapping(value="/sendSMS", method= RequestMethod.GET)
    public void sendSMS(SMS sms) {
        smsService.sendSMS(sms);
    }

    @RequestMapping(value = "/sendSMS", method = RequestMethod.POST)
    public void sendSMS(@RequestBody SMS sms, String s) {
        smsService.sendSMS(sms);
    }
}