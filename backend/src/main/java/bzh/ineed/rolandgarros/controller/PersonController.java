package bzh.ineed.rolandgarros.controller;

import bzh.ineed.rolandgarros.exception.BadRequestException;
import bzh.ineed.rolandgarros.exception.NotFoundException;
import bzh.ineed.rolandgarros.model.EGender;
import bzh.ineed.rolandgarros.model.Person;
import bzh.ineed.rolandgarros.repository.CountryRepository;
import bzh.ineed.rolandgarros.repository.PersonRepository;
import bzh.ineed.rolandgarros.util.CSVPlayerUtil;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api")
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    CountryRepository countryRepository;

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
        if (newPerson.getNationalityId() != null) {
            try {
                newPerson.setNationality(countryRepository.findById(newPerson.getNationalityId()).get());
            } catch (NoSuchElementException e) {
                throw new BadRequestException("Nationality not found");
            }
        }
        if (newPerson.getCoachId() != null) {
            Person coach = null;
            try {
                coach = personRepository.findById(newPerson.getCoachId()).get();
                if (coach.getIsCoach() == false) {
                    throw new BadRequestException("Selected person is not coach.");
                }
                newPerson.setCoach(coach);
            } catch (NoSuchElementException e) {
                throw new BadRequestException("Coach not found");
            }
        }
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

                if (newPerson.getNationalityId() != null) {
                    try {
                        person.setNationality(countryRepository.findById(newPerson.getNationalityId()).get());
                    } catch (NoSuchElementException e) {
                        throw new BadRequestException("Nationality not found");
                    }
                }
                if (newPerson.getCoachId() != null) {
                    Person coach = null;
                    try {
                        coach = personRepository.findById(newPerson.getCoachId()).get();
                        if (coach.getIsCoach() == false) {
                            throw new BadRequestException("Selected person is not coach.");
                        }
                        person.setCoach(coach);
                    } catch (NoSuchElementException e) {
                        throw new BadRequestException("Coach not found");
                    }
                }

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

    @PostMapping("/players/upload")
    ResponseEntity<?> uploadPlayers(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (CSVPlayerUtil.hasCSVFormat(file)) {
            try {
                List<Person> players = CSVPlayerUtil.csvToPlayers(file.getInputStream(), countryRepository);
                personRepository.saveAll(players);

                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(message);
            } catch (Exception e) {
                System.out.println("CSV upload error: " + e.getMessage());
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
            }
        }

        message = "Please upload a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }
}
