package bzh.ineed.rolandgarros.repository;

import bzh.ineed.rolandgarros.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Long>  {

    Country findByName(String name);

    Country findByAlpha2Code(String alpha2Code);
}
