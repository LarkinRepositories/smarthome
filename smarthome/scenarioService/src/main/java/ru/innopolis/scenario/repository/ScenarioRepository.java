package ru.innopolis.scenario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.innopolis.scenario.model.Scenario;

@Repository
public interface ScenarioRepository extends JpaRepository<Scenario, Long> {
}
