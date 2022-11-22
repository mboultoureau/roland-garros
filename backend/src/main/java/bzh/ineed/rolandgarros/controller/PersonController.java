package bzh.ineed.rolandgarros.controller;

import bzh.ineed.rolandgarros.exception.NotFoundException;
import bzh.ineed.rolandgarros.model.EGender;
import bzh.ineed.rolandgarros.model.Person;
import bzh.ineed.rolandgarros.repository.PersonRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:9000")
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    @GetMapping("/persons")
    public Page<Person> persons(
            @ParameterObject Pageable pageable
    ) {
        return personRepository.findAll(pageable);
    }

    @GetMapping("/coaches")
    public Page<Person> coaches(
            @ParameterObject Pageable pageable
    ) {
        return personRepository.findByIsCoachTrue(pageable);
    }

    @GetMapping("/players")
    public Page<Person> players(
        @ParameterObject Pageable pageable,
        @RequestParam(defaultValue = "") String gender
    ) {
        if (gender.equals("MALE")) {
            return personRepository.findByIsPlayerTrueAndGenderEquals(EGender.MALE, pageable);
        } else if (gender.equals("FEMALE")) {
            return personRepository.findByIsPlayerTrueAndGenderEquals(EGender.FEMALE, pageable);
        } else {
            return personRepository.findByIsPlayerTrue(pageable);
        }
    }

    // Create person
    @PostMapping("/persons")
    Person newPerson(@RequestBody Person newPerson) {
        return personRepository.save(newPerson);
    }

    @GetMapping("/persons/{id}")
    Person one(@PathVariable Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Could not find person " + id));
    }

    @PutMapping("/persons/{id}")
    Person replace(@RequestBody Person newPerson, @PathVariable Long id) {
        return personRepository.findById(id)
            .map(person -> {
                person.setFirstname(newPerson.getFirstname());
                person.setLastname(newPerson.getLastname());
                person.setGender(newPerson.getGender());
                person.setNationality(newPerson.getNationality());
                person.setBirthDate(person.getBirthDate());
                person.setBirthPlace(newPerson.getBirthPlace());
                person.setWeight(newPerson.getWeight());
                person.setHeight(newPerson.getHeight());
                person.setPicture(newPerson.getPicture());
                person.setIsPlayer(newPerson.getIsPlayer());
                person.setIsCoach(newPerson.getIsCoach());
                return personRepository.save(person);
            })
            .orElseGet(() -> {
                return personRepository.save(newPerson);
            });
    }

    @DeleteMapping("/persons/{id}")
    void delete(@PathVariable Long id) {
        personRepository.deleteById(id);
    }
}
