package bzh.ineed.rolandgarros.repository;

import bzh.ineed.rolandgarros.model.Court;
import bzh.ineed.rolandgarros.model.ERound;
import bzh.ineed.rolandgarros.model.EType;
import bzh.ineed.rolandgarros.model.Match;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.time.LocalDateTime;
import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {

    List<Match> findByTournamentIdAndTypeAndRound(Long id, EType type, ERound round);

    List<Match> findAllByCourtAndStartDateBetween(Court court, LocalDateTime startDate, LocalDateTime endDate);

    Page<Match> findByRoundAndTypeAndTournamentId(ERound round, EType type, Long tournamentId, Pageable pageable);
}
