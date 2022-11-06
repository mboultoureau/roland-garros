package bzh.ineed.rolandgarros.repository;

import bzh.ineed.rolandgarros.model.EGender;
import bzh.ineed.rolandgarros.model.Player;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findByGender(EGender gender, Sort sort);
}
