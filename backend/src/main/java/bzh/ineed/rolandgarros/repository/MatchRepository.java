package bzh.ineed.rolandgarros.repository;

import bzh.ineed.rolandgarros.model.ERound;
import bzh.ineed.rolandgarros.model.EType;
import bzh.ineed.rolandgarros.model.Match;
import bzh.ineed.rolandgarros.model.Tournament;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {
    //Match findByType(EType type);   //Pas ok
    //Match findByRound(ERound round);
    List<Match> findByType(EType type);

    List<Match> findByTypeAndId(EType type, Integer id);

    List<Match> findByTournamentAndType(Tournament tournament,EType type);

    List<Match> findByTypeAndRound(EType type, ERound round);

    List<Match> findByTournamentAndTypeAndRound(Tournament tournament,EType type, ERound round);

    List<Match> findByTournamentIdAndTypeAndRound(Long id, EType type, ERound round);

    List<Match> findByTournament(Tournament tournament);
}
