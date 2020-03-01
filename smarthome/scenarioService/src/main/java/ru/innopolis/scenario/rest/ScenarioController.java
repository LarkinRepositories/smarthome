package ru.innopolis.scenario.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.innopolis.scenario.model.Scenario;
import ru.innopolis.scenario.repository.ScenarioRepository;

@RestController
@RequestMapping("/")
public class ScenarioController {

    private ScenarioRepository scenarioRepository;

    @GetMapping("/hello")
    @LoadBalanced
    public String getHello() {
        return "Hello from scenario service, you are authorized";
    }

}
