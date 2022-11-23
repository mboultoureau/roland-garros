package bzh.ineed.rolandgarros.controller;

import bzh.ineed.rolandgarros.model.*;
import bzh.ineed.rolandgarros.model.Team;
import bzh.ineed.rolandgarros.repository.MatchRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
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

import static bzh.ineed.rolandgarros.model.EType.SIMPLE_MEN;
import static bzh.ineed.rolandgarros.model.EType.SIMPLE_WOMEN;
import static bzh.ineed.rolandgarros.model.EType.DOUBLE_MEN;
import static bzh.ineed.rolandgarros.model.EType.DOUBLE_WOMAN;

@RestController
@RequestMapping("/api")
public class MatchController {
    @Autowired
    MatchRepository matchRepository;

    @GetMapping("/matchs")
    public ResponseEntity<?> index(
            @RequestParam(defaultValue = "") Integer id,
            // @RequestParam(defaultValue = "null") Tournament tournament,
            @RequestParam(defaultValue = "") Integer tournamentID,
            @RequestParam(defaultValue = "") Integer year,
            @RequestParam(defaultValue = "UNDEFINED") String status,
            @RequestParam(defaultValue = "") String round,
            @RequestParam(defaultValue = "") String date,
            @RequestParam(defaultValue = "") String teamAid,
            @RequestParam(defaultValue = "") String teamBid,
            @RequestParam(defaultValue = "") String court,
            @RequestParam(defaultValue = "null") String type,
            // @RequestParam(defaultValue = "null") EType type,
            @RequestParam(defaultValue = "id,desc") String sortBy[]
    ) {
        //System.out.println("TEST 1");
        EType eType = null;
        switch (type) {
            case "SIMPLE_MEN":
                eType = EType.SIMPLE_MEN;
                break;
            case "SIMPLE_WOMEN":
                eType = EType.SIMPLE_WOMEN;
                break;
            case "DOUBLE_MEN":
                eType = EType.DOUBLE_MEN;
                break;
            case "DOUBLE_WOMEN":
                eType = EType.DOUBLE_WOMAN;
                break;
        }
        ERound eRound = null;
        switch (round) {
            case "FIRST_ROUND":
                eRound = ERound.FIRST_ROUND;
                break;
            case "SECOND_ROUND":
                eRound = ERound.SECOND_ROUND;
                break;
            case "THIRD_ROUND":
                eRound = ERound.THIRD_ROUND;
                break;
            case "SIXTEENTH_ROUND":
                eRound = ERound.SIXTEENTH_ROUND;
                break;
            case "QUART_FINAL":
                eRound = ERound.QUART_FINAL;
                break;
            case "SEMI_FINAL":
                eRound = ERound.SEMI_FINAL;
                break;
            case "FINAL_ROUND":
                eRound = ERound.FINAL_ROUND;
                break;
        }

        try{
            Map<String, Object> response = new HashMap<>();
            System.out.println(type);
            if(type != null && round != null){
                // Find match by type and round
                System.out.println("0");
                response.put("match", matchRepository.findByTypeAndRound(eType, eRound) );
            }
            else if(type != null && id != null){
                // Find a specific match with type and id
                System.out.println("2");
                response.put("match", matchRepository.findByTypeAndId(eType, id));
            }
            else if(type != null){
                // Find all match with type
                System.out.println("3");
                response.put("match", matchRepository.findByType(eType));
            }
            else {
                // Find all match
                System.out.println("4");
                response.put("match", matchRepository.findAll());
            }
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
        //return new ResponseEntity<>(null, HttpStatus.OK);
    }

}
