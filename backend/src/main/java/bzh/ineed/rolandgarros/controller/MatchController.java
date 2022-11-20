package bzh.ineed.rolandgarros.controller;

import bzh.ineed.rolandgarros.model.*;
import bzh.ineed.rolandgarros.model.Team;
import bzh.ineed.rolandgarros.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class MatchController {
    @Autowired
    MatchRepository matchRepository;

    @GetMapping("/matchs")
    public ResponseEntity<?> index(
            @RequestParam(defaultValue = "0") String tournament,
            @RequestParam(defaultValue = "UNDEFINED") String status,
            @RequestParam(defaultValue = "") String round,
            @RequestParam(defaultValue = "null") String date,
            @RequestParam(defaultValue = "null") String teamAid,
            @RequestParam(defaultValue = "null") String teamBid,
            @RequestParam(defaultValue = "null") String court,
            @RequestParam(defaultValue = "") String type,
            @RequestParam(defaultValue = "id,desc") String sortBy[]
    ) {
        try{
            System.out.println("ICI3");
            Map<String, Object> response = new HashMap<>();
            response.put("match", matchRepository.findAll());
            response.put("success", true);


            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (PropertyReferenceException e) {
            System.out.println("ICI1");
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            System.out.println("ICI2");
            System.out.println(e.getClass());
            System.out.println(e.getMessage());
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/creatematch")
    public String postBody(@RequestBody String fullName) {
        return "Hello " + fullName;
    }


    @PostMapping(
            value = "/createDefaultMatchForTournament",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Match> postBody(@RequestBody Match match) {
        Match persistedMatch = matchRepository.save(match);
        return ResponseEntity
                .created(URI
                        .create(String.format("/matches/%s", match.getCourtId() )))
                .body(persistedMatch);
    }

    /*
    @PostMapping(
            value = "/createMatchFromTournamentStatusRound",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Match> postBody(@RequestBody Match match) {
        Match persistedMatch = matchRepository.save(match);
        return ResponseEntity
                .created(URI
                        .create(String.format("/matches/%s", match.getTournament() )))
                .body(persistedMatch);
    }*/
}
