package bzh.ineed.rolandgarros.controller;

import bzh.ineed.rolandgarros.model.EGender;
import bzh.ineed.rolandgarros.model.EType;
import bzh.ineed.rolandgarros.model.Tournament;
import bzh.ineed.rolandgarros.repository.TournamentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.*;

@RestController
@RequestMapping("/api")
public class TournamentController {
    @Autowired
    TournamentRepository tournamentRepository;

    @GetMapping("/tournaments")
    public ResponseEntity<?> index(
            @RequestParam(defaultValue = "2022") Integer year,
            @RequestParam(defaultValue = "id,desc") String sortBy[]
    ) {
        try{
            Map<String, Object> response = new HashMap<>();
            response.put("tournament", tournamentRepository.findAll());
            response.put("success", true);


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
