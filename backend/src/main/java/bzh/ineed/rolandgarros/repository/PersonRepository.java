package bzh.ineed.rolandgarros.repository;

import bzh.ineed.rolandgarros.model.EGender;
import bzh.ineed.rolandgarros.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {
    // Page<Person> findByGenderAndPlayer(EGender gender, boolean isPlayer, Pageable pagingSort);

    Page<Person> findByIsPlayerTrue(Pageable pageable);

    Page<Person> findByIsPlayerTrueAndGenderEquals(EGender gender, Pageable pageable);
}
