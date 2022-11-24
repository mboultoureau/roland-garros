package bzh.ineed.rolandgarros.repository;

import bzh.ineed.rolandgarros.model.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {
    Optional<Tournament> findByYear(Integer year);
}
