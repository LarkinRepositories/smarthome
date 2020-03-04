package ru.innopolis.scenario.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.scenario.model.Scenario;
import ru.innopolis.scenario.model.Status;
import ru.innopolis.scenario.model.Type;
import ru.innopolis.scenario.repository.ScenarioRepository;
import ru.innopolis.scenario.repository.TypeRepository;
import ru.innopolis.scenario.service.ScenarioService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class ScenarioServiceImpl implements ScenarioService {

    private ScenarioRepository scenarioRepository;
    private TypeRepository typeRepository;

    @Autowired
    public ScenarioServiceImpl(ScenarioRepository scenarioRepository, TypeRepository typeRepository) {
        this.scenarioRepository = scenarioRepository;
        this.typeRepository = typeRepository;
    }

    @Override
    public Scenario addScenario(Scenario scenario) {
        Type scenarioType = typeRepository.findByName("SCHEDULED");
        List<Type> scenarioTypes = new ArrayList<>();
        scenarioTypes.add(scenarioType);
        scenario.setTypes(scenarioTypes);
        scenario.setCreated(LocalDateTime.now());
        scenario.setUpdated(LocalDateTime.now());
        scenario.setRunTime(LocalDateTime.now());
        scenario.setStatus(Status.ACTIVE);
        Scenario registeredScenario = scenarioRepository.save(scenario);
        return registeredScenario;
    }

    @Override
    public List<Scenario> getAll() {
        List<Scenario> scenarios = scenarioRepository.findAll();
        log.info("IN getAll - {} scenarios found", scenarios.size());
        return scenarios;
    }

    @Override
    public Scenario getScenario(Long id) {
        Scenario scenario = scenarioRepository.findById(id).orElse(null);
        return scenario;
    }

    @Override
    public List<Scenario> findByUserId(Long userId) {
        List<Scenario> scenarios = scenarioRepository.findAllByUserId(userId);
        log.info("In findByUserId - {} scenarios found with userId: {}", scenarios.size(), userId);
        return scenarios;
    }

    @Override
    public List<Scenario> findByDeviceId(Long deviceId) {
        List<Scenario> scenarios = scenarioRepository.findAllByDeviceId(deviceId);
        log.info("In finByDeviceId - {} scenarios found with deviceId: {} ", scenarios.size(), deviceId);
        return scenarios;
    }

    @Override
    public boolean update(Long id, String alias, Long deviceId, List<String> typeNames) {
       Scenario scenario = scenarioRepository.findById(id).orElse(null);
       if (scenario != null) {
           scenario.setAliasName(alias);
           scenario.setDeviceId(deviceId);
           List<Type> scenarioTypes = new ArrayList<>();
           typeNames.forEach(e -> scenarioTypes.add(typeRepository.findByName(e)));
           scenario.setTypes(scenarioTypes);
           scenario.setUpdated(LocalDateTime.now());
           scenarioRepository.save(scenario);
           log.info("In update: scenario with id {} successfully updated", id);
           return true;
       }
        log.warn("In update: there's no scenario with id {}", id);
        return false;
    }

    @Override
    public boolean delete(Long id) {
        Scenario scenario = scenarioRepository.findById(id).orElse(null);
        if (scenario != null) {
            scenario.setStatus(Status.DELETED);
            scenario.setUpdated(LocalDateTime.now());
            scenarioRepository.save(scenario);
            log.info("In delete: scenario with id {} successfully deleted", id);
            return true;
        }
        log.warn("In delete: scenario with id {} not found", id);
        return false;
    }

    @Override
    public boolean updateStatus(Long id) {
        Scenario scenario = scenarioRepository.findById(id).orElse(null);
        if (scenario != null) {
            scenario.setStatus(Status.NOT_ACTIVE);
            log.info("Status.NOT_ACTIVE");
            return true;
        }
        return false;
    }
}
