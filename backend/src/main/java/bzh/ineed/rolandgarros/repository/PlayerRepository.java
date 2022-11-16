package bzh.ineed.rolandgarros.repository;

import bzh.ineed.rolandgarros.model.EGender;
import bzh.ineed.rolandgarros.model.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    Page<Player> findByGender(EGender gender, Pageable pageable);

    Page<Player> findAllBy(Pageable pageable);
}
