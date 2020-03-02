package ru.innopolis.scenario.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.innopolis.scenario.model.Scenario;
import ru.innopolis.scenario.service.ScenarioService;

@RestController
@RequestMapping("/")
public class ScenarioController {

    @Autowired
    private ScenarioService scenarioService;

    @PostMapping("/scenario/run/")
    public String runScenario(@RequestParam(name="id")Long scenarioID) {
        Scenario scenario = scenarioService.getScenario(scenarioID);
        Long userId = scenario.getUserId();
        Long deviceID = scenario.getDeviceId();
        Long commandID = scenario.getCommandId();
       // String url = "http://" +
        //todo как-то нужно пробрасывать или даже выполнять у deviceID комманду commandID по scheduler

        return null;
    }

//    @PostMapping("/scenario/run/")
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
