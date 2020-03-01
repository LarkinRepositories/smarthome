package ru.innopolis.scenario.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.innopolis.scenario.model.Scenario;
import ru.innopolis.scenario.repository.ScenarioRepository;

import java.util.List;


@Service
@Slf4j
public class ScenarioServiceImpl implements ScenarioService {

    private ScenarioRepository scenarioRepository;

    @Override
    public Scenario addScenario(Scenario scenario) {
        return null;
    }

    @Override
    public List<Scenario> getAll() {
        List<Scenario> scenarios = scenarioRepository.findAll();
        log.info("IN getAll - {} scenarios found", scenarios.size());
        return scenarios;
    }

    @Override
    public List<Scenario> findByUserId(Long userId) {
        return null;
    }

    @Override
    public List<Scenario> findByDeviceId(Long deviceId) {
        return null;
    }

    @Override
    public boolean update(Long id, String alias, List<String> typeNames) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
