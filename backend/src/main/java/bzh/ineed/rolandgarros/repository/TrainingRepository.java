package bzh.ineed.rolandgarros.repository;

import bzh.ineed.rolandgarros.model.Training;
import bzh.ineed.rolandgarros.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrainingRepository extends JpaRepository<Training, Long> {
}
