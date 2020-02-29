package deviceService.repository;

import deviceService.model.Command;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandRepository extends JpaRepository<Command, Long> {
   Command findByName(String name);

}
