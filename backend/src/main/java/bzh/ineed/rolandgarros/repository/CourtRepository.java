package bzh.ineed.rolandgarros.repository;

import bzh.ineed.rolandgarros.model.Court;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourtRepository extends JpaRepository<Court, Long> {
    Court findByName(String name);
}
