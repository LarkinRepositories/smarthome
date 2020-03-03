package ru.innopolis.scenario.rest;

import liquibase.pro.packaged.S;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.support.CronSequenceGenerator;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.innopolis.scenario.model.Scenario;
import ru.innopolis.scenario.model.Status;
import ru.innopolis.scenario.service.ScenarioService;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
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

    public void runScenario() {
        List<Scenario> scenarioList = scenarioService.getAll();

        List<Scenario> scenarioListActive = scenarioList.stream()
                .filter(s->s.getStatus().equals(Status.ACTIVE)).collect(Collectors.toList());



        for (int i = 0; i < scenarioListActive.size(); i++) {

            Scenario scenario = scenarioListActive.get(i);
            scenario.getRunTime();
            scenario.getDeviceId();
            scenario.getCommandId();

        }



        log.info("Current time is :: " + Calendar.getInstance().getTime());
    }

    public void runTimeTask(Date desiredDate,Long deviceId,Long commandId){
        Date now = new Date();
        long delay = desiredDate.getTime() - now.getTime();

        ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
        ses.schedule(new Runnable(){
            @Override
            public void run() {
                // + do other things?
            }
        }, delay, TimeUnit.MILLISECONDS); // run in "delay" millis
    }

    public Date plusRepeatInterval() {
        return null;
    }

//    @PostMapping("/devices/on/")
//    public String turnDeviceOn(@RequestParam(name ="id")Long deviceId) {
//        Device device = deviceService.getDevice(deviceId);
//        Long commandId = device.getCommands().get(0).getCommandId();
//        String url = "http://mqtt-service/test/on/" +
//                "?ip=" + device.getIp() +
//                "&port=" + device.getPort() +
//                "&commandId="+ commandId +
//                "&device=" + device.getAliasName();
//        return restTemplate.getForObject(url, String.class);
//    }
}
