package bzh.ineed.rolandgarros.repository;

import bzh.ineed.rolandgarros.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match, Long> {
    //Match findByType(EType type);

    //Match findByRound(ERound round);
}
