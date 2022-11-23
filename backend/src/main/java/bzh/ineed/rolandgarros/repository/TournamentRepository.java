package bzh.ineed.rolandgarros.repository;

import bzh.ineed.rolandgarros.model.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {
    Object findByYear(Integer year); // Pas ok
    Object findAllById(Integer id); // OK
}
