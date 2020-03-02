package ru.innopolis.scenario.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.scenario.model.Scenario;
import ru.innopolis.scenario.service.ScenarioService;

import java.util.List;

@RestController
@RequestMapping("/")
public class CrudController {
    @Autowired
    private ScenarioService scenarioService;

    @GetMapping("/hello")
    @LoadBalanced
    public String getHello() {
        return "Hello from scenario service, you are authorized";
    }

    @GetMapping("/scenarios/get/")
    @LoadBalanced
    public List<Scenario> getScenarios(@RequestParam(name = "userId") Long userId) {
        List<Scenario> scenarios = scenarioService.findByUserId(userId);
        return scenarios;
    }

    @PostMapping("/scenarios/add/")
    public void addScenario(@RequestParam(name = "alias")String alias,
                            @RequestParam(name = "userId") Long userId,
                            @RequestParam(name = "deviceId")Long deviceId) {
        Scenario scenario = new Scenario();
        scenario.setAliasName(alias);
        scenario.setUserId(userId);
        scenario.setDeviceId(deviceId);
        scenarioService.addScenario(scenario);
    }

    @PostMapping("/scenarios/update/")
    public String updateScenario(@RequestParam(name = "id")Long id,
                               @RequestParam(name = "alias")String alias,
                               @RequestParam(name = "deviceId")Long deviceId,
                               @RequestParam(name = "types")List<String> types) {
        scenarioService.update(id, alias, deviceId, types);
        String resultString = "Scenario with id: "+id+" successfully updated";
        return resultString;
    }


    @PostMapping("/scenarios/delete")
    public String removeScenario(@RequestParam(name = "id")Long id) {
        scenarioService.delete(id);
        String resultString = "Scenario with id: "+id+" successfully deleted";
        return resultString;
    }
}
