package bzh.ineed.rolandgarros.repository;

import bzh.ineed.rolandgarros.model.Court;
import bzh.ineed.rolandgarros.model.Training;
import bzh.ineed.rolandgarros.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TrainingRepository extends JpaRepository<Training, Long> {
    List<Training> findByCourtAndStartDateBetween(Court court, LocalDateTime startDate, LocalDateTime endDate);

    List<Training> findAllByCourtAndStartDateBetween(Court court, LocalDateTime startDate, LocalDateTime endDate);
}
