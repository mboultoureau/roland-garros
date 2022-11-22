package bzh.ineed.rolandgarros.controller;

import bzh.ineed.rolandgarros.exception.NotFoundException;
import bzh.ineed.rolandgarros.model.Country;
import bzh.ineed.rolandgarros.model.EGender;
import bzh.ineed.rolandgarros.model.Person;
import bzh.ineed.rolandgarros.repository.CountryRepository;
import bzh.ineed.rolandgarros.repository.PersonRepository;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CountryController {

    @Autowired
    CountryRepository countryRepository;

    @GetMapping("/countries")
    public List<Country> countries() {
        return countryRepository.findAll();
    }
}
