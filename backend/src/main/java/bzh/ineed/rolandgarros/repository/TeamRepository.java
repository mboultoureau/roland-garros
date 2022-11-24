package bzh.ineed.rolandgarros.repository;

import bzh.ineed.rolandgarros.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
