package bzh.ineed.rolandgarros.repository;

import bzh.ineed.rolandgarros.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Collection;
import java.time.LocalDateTime;
import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {

    List<Match> findByTournamentIdAndTypeAndRound(Long id, EType type, ERound round);

    List<Match> findAllByCourtAndStartDateBetween(Court court, LocalDateTime startDate, LocalDateTime endDate);

    Page<Match> findByRoundAndTypeAndTournamentId(ERound round, EType type, Long tournamentId, Pageable pageable);


    @Query("SELECT DISTINCT m.type FROM Match m WHERE m.tournament = ?1")
    Collection<String> findAllTypes(Tournament id);

    List<Match> findAllByTournamentAndTypeAndRound(Tournament tournament, EType type, ERound round);
}
