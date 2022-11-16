package bzh.ineed.rolandgarros.controller;

import bzh.ineed.rolandgarros.exception.NotFoundException;
import bzh.ineed.rolandgarros.model.EGender;
import bzh.ineed.rolandgarros.model.Person;
import bzh.ineed.rolandgarros.repository.PersonRepository;
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
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    private Sort.Direction getSortDirection(String direction) {
        if (direction.equals("asc")) {
            return Sort.Direction.ASC;
        } else if (direction.equals("desc")) {
            return Sort.Direction.DESC;
        }

        return Sort.Direction.ASC;
    }

    /*
    @GetMapping("/coaches")
    Page<Person> coaches(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        Page<Person> pageCoaches = null;

        try {
            List<Order> orders = new ArrayList<Order>();
            orders.add(new Order(Sort.Direction.ASC, "firstname"));
            orders.add(new Order(Sort.Direction.ASC, "lastname"));

            // Paging
            Pageable pagingSort = PageRequest.of(page - 1, size, Sort.by(orders));
            pageCoaches = personRepository.findAllByPlayerEquals(true, pagingSort);

            Map<String, Object> response = new HashMap<>();
            response.put("coaches", pageCoaches.getContent());
            response.put("currentPage", page);
            response.put("totalItems", pageCoaches.getTotalElements());
            response.put("totalPages", pageCoaches.getTotalPages());
            response.put("success", true);
            if (size > 0) {
                response.put("firstPage", 0);
                response.put("lastPage", pageCoaches.getTotalPages() / size + 1);
            } else {
                response.put("firstPage", 0);
                response.put("lastPage", 0);
            }

            return pageCoaches;
        } catch (PropertyReferenceException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("error", e.getMessage());
            return pageCoaches;
        } catch (Exception e) {
            System.out.println(e.getClass());
            System.out.println(e.getMessage());
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            return pageCoaches;
        }
    }

     */

    @GetMapping("/players")
    public Page<Person> players(
        @ParameterObject Pageable pageable,
        @RequestParam(defaultValue = "") String gender
    ) {
        if (gender.equals("male")) {
            return personRepository.findByIsPlayerTrueAndGenderEquals(EGender.MALE, pageable);
        } else if (gender.equals("female")) {
            return personRepository.findByIsPlayerTrueAndGenderEquals(EGender.FEMALE, pageable);
        } else {
            return personRepository.findByIsPlayerTrue(pageable);
        }
    }

    /*
    @GetMapping("/players")
    public Page<Person> players(
            @RequestParam(defaultValue = "") String gender,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "id,desc") String sortBy[]
    ) {
        Page<Person> pagePlayers;

        try {
            List<Order> orders = new ArrayList<Order>();

            // Sort
            if (sortBy[0].contains(",")) {
                // sortOrder="field, direction"
                for (String sortOrder : sortBy) {
                    String[] _sort = sortOrder.split(",");
                    orders.add(new Order(getSortDirection(_sort[1]), _sort[0]));
                }
            } else {
                // sort=[field, direction]
                orders.add(new Order(getSortDirection(sortBy[1]), sortBy[0]));
            }

            // Paging
            Pageable pagingSort = PageRequest.of(page - 1, size, Sort.by(orders));

            // Filter by gender
            if (gender.equals("woman")) {
                pagePlayers = personRepository.findByGenderEqualsAndPlayerEquals(EGender.FEMALE, true, pagingSort);
            } else if (gender.equals("man")) {
                pagePlayers = personRepository.findByGenderEqualsAndPlayerEquals(EGender.MALE, true, pagingSort);
            } else {
                pagePlayers = personRepository.findAllBy(pagingSort);
            }

            Map<String, Object> response = new HashMap<>();
            response.put("players", pagePlayers.getContent());
            response.put("currentPage", page);
            response.put("totalItems", pagePlayers.getTotalElements());
            response.put("totalPages", pagePlayers.getTotalPages());
            response.put("success", true);
            if (size > 0) {
                response.put("firstPage", 0);
                response.put("lastPage", pagePlayers.getTotalPages() / size + 1);
            } else {
                response.put("firstPage", 0);
                response.put("lastPage", 0);
            }

            return pagePlayers;
        } catch (PropertyReferenceException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("error", e.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println(e.getClass());
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            return null;
        }
    }
     */

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
                return personRepository.save(person);
            })
            .orElseGet(() -> {
                newPerson.setId(id);
                return personRepository.save(newPerson);
            });
    }

    @DeleteMapping("/persons/{id}")
    void delete(@PathVariable Long id) {
        personRepository.deleteById(id);
    }
}
