package bzh.ineed.rolandgarros.repository;

import bzh.ineed.rolandgarros.model.Score;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreRepository extends JpaRepository<Score, Long> {
}
