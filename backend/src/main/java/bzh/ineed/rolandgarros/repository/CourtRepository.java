package bzh.ineed.rolandgarros.repository;

import bzh.ineed.rolandgarros.model.Court;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CourtRepository extends JpaRepository<Court, Long> {
    Optional<Court> findByName(String name);
}
