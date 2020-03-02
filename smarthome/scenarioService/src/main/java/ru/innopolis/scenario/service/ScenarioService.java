package ru.innopolis.scenario.service;

import ru.innopolis.scenario.model.Scenario;

import java.util.List;

public interface ScenarioService {
    Scenario addScenario(Scenario scenario);
    List<Scenario> getAll();
    Scenario getScenario(Long id);
    List<Scenario> findByUserId(Long userId);
    List<Scenario> findByDeviceId(Long deviceId);
    boolean update(Long id, String alias, Long deviceId, List<String> typeNames);
    boolean delete(Long id);
}
