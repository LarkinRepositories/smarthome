package ru.innopolis.scenario.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
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
    //yo
    @Autowired
    private ScenarioService scenarioService;
    @Autowired
    private RestTemplate restTemplate;

    private int MINUTES_TO_ADD = 1;
    boolean runOnce = false;


    @Scheduled(cron = "0/2 * * * * ?")
    public void runScenario() {

        Date nowDate = new Date();
        List<Scenario> scenarioList = scenarioService.getAll();

        List<Scenario> scenarioListActive = scenarioList.stream()
                .filter(s -> s.getStatus().equals(Status.ACTIVE)).collect(Collectors.toList());

        for (int i = 0; i < scenarioListActive.size(); i++) {

            Scenario scenario = scenarioListActive.get(i);
            Date modifyDate = convertToDateViaInstant(scenario.getRunTime());

            runTimeTask(modifyDate, nowDate, scenario);
        }


    }


    public void runTimeTask(Date desiredDate, Date now, Scenario scenario) {
        if (desiredDate.after(now)) {
            log.debug("scenario" + scenario.getAliasName()
                    + " __ commandID - " + scenario.getCommandId() + " событие не наступило");
        } else {
            log.info("scenarioId" + scenario.getAliasName()
                    + " __ commandID - " + scenario.getCommandId() + " событие наступило!");

            toggleDevice(scenario.getId());
        }

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

    @PostMapping("/scenario/toggle")
    public String toggleDevice(Long scenarioId) {
        Scenario scenario = scenarioService.getScenario(scenarioId);
        Long deviceId = scenario.getDeviceId();
        String toggle = "";
        if (scenario.getCommandId() == 1) {
            toggle = "on";
            // scenarioService.updateStatus(scenarioId);
        }
        if (scenario.getCommandId() == 0) {
            toggle = "off";
            // scenarioService.updateStatus(scenarioId);
        }

        String url = "http://device-service/devices/" + toggle + "/" +
                "?id=" + deviceId;
        log.info(url);
        //  return null;
        return restTemplate.getForObject(url, String.class);
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
}
