package bzh.ineed.rolandgarros.controller;

import bzh.ineed.rolandgarros.model.Country;
import bzh.ineed.rolandgarros.repository.CountryRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CountryController {

    @Autowired
    CountryRepository countryRepository;

    @GetMapping("/countries")
    @Tag(name = "Countries", description = "List of countries with codes for obtaining flags")
    @Operation(description = "The complete list of country names and their alpha codes")
    public List<Country> countries() {
        return countryRepository.findAll();
    }
}
