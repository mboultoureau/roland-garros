package bzh.ineed.rolandgarros.repository;

import bzh.ineed.rolandgarros.model.EGender;
import bzh.ineed.rolandgarros.model.EType;
import bzh.ineed.rolandgarros.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
    // Page<Person> findByGenderAndPlayer(EGender gender, boolean isPlayer, Pageable pagingSort);

    Page<Person> findByIsPlayerTrue(Pageable pageable);

    Page<Person> findByIsCoachTrue(Pageable pageable);

    Page<Person> findByIsPlayerTrueAndGenderEquals(EGender gender, Pageable pageable);

    Optional<Person> findByFirstnameAndLastname(String firstname, String lastname);

    @Query("SELECT p FROM Person p WHERE p.firstname = ?1 AND p.lastname = ?2")
    Collection<Person> findAllByTournamentIdAndType(Long tournamentId, EType type);

    @Query(value = "SELECT p FROM Person p INNER JOIN Team t ON t.personA = p OR t.personB = p INNER JOIN Match m ON m.teamA = t OR m.teamB = t WHERE m.tournament.id = ?1 AND p.isPlayer = TRUE")
    List<Person> findByTournamentId(Long tournamentId);

    @Query(value = "SELECT p FROM Person p INNER JOIN Team t ON t.personA = p OR t.personB = p INNER JOIN Match m ON m.teamA = t OR m.teamB = t WHERE m.tournament.id = ?1 AND p.isPlayer = TRUE AND m.type = ?2")
    List<Person> findByTournamentIdAndType(Long tournamentId, EType type);
}
