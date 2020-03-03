package ru.innopolis.scenario.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ru.innopolis.scenario.model.Scenario;
import ru.innopolis.scenario.model.Status;
import ru.innopolis.scenario.service.ScenarioService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
@Slf4j
public class ScenarioController {

    @Autowired
    private ScenarioService scenarioService;
    @Autowired
    private RestTemplate restTemplate;

    private  int  MINUTES_TO_ADD = 1;
    boolean mayIAddMinutes = false;

    @Scheduled(cron = "0/2 * * * * ?")
    public void runScenario() {

        Date nowDate = new Date();

        List<Scenario> scenarioList = scenarioService.getAll();

        List<Scenario> scenarioListActive = scenarioList.stream()
                .filter(s->s.getStatus().equals(Status.ACTIVE)).collect(Collectors.toList());

        for (int i = 0; i < scenarioListActive.size(); i++) {

            Scenario scenario = scenarioListActive.get(i);
            Date modifyDate =  convertToDateViaInstant(scenario.getRunTime());

            if (mayIAddMinutes = false) {
                runTimeTask(modifyDate,nowDate,scenario.getId(),scenario.getCommandId());
            } else {
                log.info("wait time plus");
            }


        }




    }

    public void runTimeTask(Date desiredDate,Date now,Long scenarioId,Long commandID){
        long delay = desiredDate.getTime() - now.getTime();
        ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
        ses.schedule(new Runnable(){
            @Override
            public void run() {
                if (commandID==1){
                    turnDeviceOn(scenarioId);
                    mayIAddMinutes = true;
                }
                if (commandID == 0) {
                    turnDeviceOff(scenarioId);
                    mayIAddMinutes = true;
                } else {

                }
            }

        }, delay, TimeUnit.MILLISECONDS); // run in "delay" millis
    }


    public String turnDeviceOn(Long scenarioId) {
        Scenario scenario = scenarioService.getScenario(scenarioId);
        Long deviceId = scenario.getDeviceId();
        String url = "http://device-service/devices/on/" +
                "?id=" + deviceId;
        log.info(url);
        return null;
        //return restTemplate.getForObject(url, String.class);
    }

    public String turnDeviceOff(Long scenarioId) {
        Scenario scenario = scenarioService.getScenario(scenarioId);
        Long deviceId = scenario.getDeviceId();
        String url = "http://device-service/devices/off/" +
                "?id=" + deviceId;
        log.info(url);
        //return restTemplate.getForObject(url, String.class);
        return null;
    }


    public LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    Date convertToDateViaInstant(LocalDateTime dateToConvert) {
        return java.util.Date
                .from(dateToConvert.atZone(ZoneId.systemDefault())
                        .toInstant());
    }


    public Date addHoursToJavaUtilDate(Date date, int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, hours);
        return calendar.getTime();
    }

    public Date addMinutesToJavaUtilDate(Date date, int minutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minutes);
        return calendar.getTime();
    }

}
