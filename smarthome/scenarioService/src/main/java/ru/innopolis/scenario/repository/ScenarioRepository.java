package ru.innopolis.scenario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.innopolis.scenario.model.Scenario;

import java.util.List;

@Repository
public interface ScenarioRepository extends JpaRepository<Scenario, Long> {
    Scenario findByAliasName(String aliasName);
    List<Scenario> findAllByUserId(Long userId);
    List<Scenario> findAllByDeviceId(Long deviceId);
}
