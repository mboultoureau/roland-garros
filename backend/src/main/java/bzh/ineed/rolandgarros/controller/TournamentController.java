package bzh.ineed.rolandgarros.controller;

import bzh.ineed.rolandgarros.model.Match;
import bzh.ineed.rolandgarros.model.Tournament;
import bzh.ineed.rolandgarros.repository.TournamentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Match
import bzh.ineed.rolandgarros.model.Match;
import bzh.ineed.rolandgarros.model.Match;
import bzh.ineed.rolandgarros.repository.MatchRepository;

// POST
import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.ProtocolException;
import java.net.MalformedURLException;
import java.io.OutputStreamWriter;


import java.net.URI;
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

    /* FONCTIONNE
    @PostMapping(
            value = "/createTournamentWithMatches",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Tournament> postBody(@RequestBody Tournament tournament) {
        Tournament persistedTournament = tournamentRepository.save(tournament);
        return ResponseEntity
                .created(URI
                        .create(String.format("/tournaments/%s", tournament.getYear() )))
                .body(persistedTournament);
    }*/

    @PostMapping(
            value = "/createTournamentWithMatches",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Tournament> postBody(@RequestBody Tournament tournament) throws IOException {
        int i;
        Tournament persistedTournament = tournamentRepository.save(tournament);

        // ça pourrait ếtre cool de recup le token pour ne pas avoir à le rentré à chaque fois mais pour l'instant c'est comme ça
        //tournament.POSTAuthorization();

        // Création d'un match
        tournament.POSTReq(1,0,0,32);

        // Création des matchs du tournoi. (Il manquerait de les associers à un tournoi, ainsi que de définir leurs types depuis la POST de création de tournoi).
        for(i=0;i<63;i++){
            // FIRST ROUND
            tournament.POSTReq(2,0,1,101);
            if(i>=31){
                // SECOND ROUND
                tournament.POSTReq(2,1,1,101);
            }
            if(i>=47){
                // THIRD ROUND
                tournament.POSTReq(2,2,1,101);
            }
            if(i>=55){
                // SIXTEENTH ROUND
                tournament.POSTReq(2,3,1,101);
            }
            if(i>=59){
                // QUARTER FINAL
                tournament.POSTReq(2,4,1,101);
            }
            if(i>=61){
                // SEMI FINAL
                tournament.POSTReq(2,5,1,101);
            }
            if(i>=62){
                // FINAL ROUND
                tournament.POSTReq(2,6,1,101);
            }
        }

        return ResponseEntity
                .created(URI
                        .create(String.format("/tournaments/%s", tournament.getYear() )))
                .body(persistedTournament);
    }
}
