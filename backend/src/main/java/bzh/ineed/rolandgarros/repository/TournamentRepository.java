package bzh.ineed.rolandgarros.repository;

import bzh.ineed.rolandgarros.model.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {
    //Integer findByYear(Integer year);
    Object findByYear(Integer year);

    //Integer findById(Integer tournament);

    Object findAllById(Integer id);
}
