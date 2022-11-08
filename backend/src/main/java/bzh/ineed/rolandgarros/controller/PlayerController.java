package bzh.ineed.rolandgarros.controller;

import bzh.ineed.rolandgarros.model.EGender;
import bzh.ineed.rolandgarros.model.Player;
import bzh.ineed.rolandgarros.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PlayerController {

    @Autowired
    PlayerRepository playerRepository;

    private Sort.Direction getSortDirection(String direction) {
        if (direction.equals("asc")) {
            return Sort.Direction.ASC;
        } else if (direction.equals("desc")) {
            return Sort.Direction.DESC;
        }

        return Sort.Direction.ASC;
    }

    @GetMapping("/players")
    public ResponseEntity<?> index(
            @RequestParam(defaultValue = "") String gender,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "id,desc") String sortBy[]
    ) {
        try {
            Page<Player> pagePlayers;
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
                pagePlayers = playerRepository.findByGender(EGender.FEMALE, pagingSort);
            } else if (gender.equals("man")) {
                pagePlayers = playerRepository.findByGender(EGender.MALE, pagingSort);
            } else {
                pagePlayers = playerRepository.findAllBy(pagingSort);
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

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (PropertyReferenceException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            System.out.println(e.getClass());
            System.out.println(e.getMessage());
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
