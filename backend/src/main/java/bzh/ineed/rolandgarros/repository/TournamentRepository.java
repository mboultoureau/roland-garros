package bzh.ineed.rolandgarros.repository;

import bzh.ineed.rolandgarros.model.EGender;
import bzh.ineed.rolandgarros.model.Player;
import bzh.ineed.rolandgarros.model.Tournament;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import bzh.ineed.rolandgarros.model.EType;

import java.util.Optional;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {
    Integer findByYear(Integer year);
}
