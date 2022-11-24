package bzh.ineed.rolandgarros.controller;

import bzh.ineed.rolandgarros.model.EType;
import bzh.ineed.rolandgarros.model.Person;
import bzh.ineed.rolandgarros.repository.PersonRepository;

import bzh.ineed.rolandgarros.util.ERoundFormat;
import bzh.ineed.rolandgarros.util.ETypeFormat;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import bzh.ineed.rolandgarros.model.*;
import bzh.ineed.rolandgarros.repository.TournamentRepository;

import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Match
import bzh.ineed.rolandgarros.model.Match;
import bzh.ineed.rolandgarros.repository.MatchRepository;

// POST
import javax.validation.Valid;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/api")
public class TournamentController {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    TournamentRepository tournamentRepository;

    @Autowired
    MatchRepository matchRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/tournaments")
    public ResponseEntity<?> index(@ParameterObject Pageable pageable) {
        ArrayList<Map<String, Object>> response = new ArrayList<>();
        List<Tournament> tournaments = tournamentRepository.findAll();
        List<Match> matches = matchRepository.findAll();

        System.out.println(tournaments);
        for (Tournament t : tournaments) {
            Map<String, Object> tournamentResponse = new HashMap<>();
            tournamentResponse.put("id", t.getId());
            tournamentResponse.put("year", t.getYear());
            tournamentResponse.put("types", matchRepository.findAllTypes(t));
            response.add(tournamentResponse);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/tournaments/{id}")
    public ResponseEntity<?> index(
            @PathVariable Long id,
            @RequestParam(defaultValue = "") String type,
            @RequestParam(defaultValue = "") String round
    ) {
        HashMap<String, Object> response = new HashMap<>();
        Tournament tournament = tournamentRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Tournament not found"));

        response.put("id", tournament.getId());
        response.put("year", tournament.getYear());
        response.put("types", matchRepository.findAllTypes(tournament));

        if (!type.equals("") && !round.equals("")) {
            response.put("matches", matchRepository.findAllByTournamentAndTypeAndRound(tournament, EType.valueOf(type), ERoundFormat.format(round)));
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/tournament/{id}")
    public ResponseEntity<?> index(
            @PathVariable Long id,
            @RequestParam(defaultValue = "id,desc") String sortBy[]
    ) {
        try {
            ArrayList<Map<String, Object>> response = new ArrayList<>();
            List<Tournament> tournaments = tournamentRepository.findAll();
            List<Match> matches = matchRepository.findAll();

            System.out.println(tournaments);
            for (Tournament t : tournaments) {
                Map<String, Object> tournamentResponse = new HashMap<>();
                if(t.getId() != id){

                }else{
                    tournamentResponse.put("id", t.getId());
                    tournamentResponse.put("year", t.getYear());
                    tournamentResponse.put("types",matchRepository.findAllTypes(t));
                    response.add(tournamentResponse);
                }
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

    public Tournament createMatches(Tournament tournament, EType type) {
        for(Integer i = 0; i < 63; i++){
            // FIRST ROUND
            matchRepository.save(new Match(EStatus.UNDEFINED,ERound.FIRST_ROUND,type, tournament));
            //tournament.POSTReq(status,round,type,courtId);
            if(i>=31){
                // SECOND ROUND
                matchRepository.save(new Match(EStatus.UNDEFINED,ERound.SECOND_ROUND,type, tournament));
            }
            if(i>=47){
                // THIRD ROUND
                matchRepository.save(new Match(EStatus.UNDEFINED,ERound.THIRD_ROUND,type, tournament));
            }
            if(i>=55){
                // SIXTEENTH ROUND
                matchRepository.save(new Match(EStatus.UNDEFINED,ERound.SIXTEENTH_ROUND,type,tournament));
            }
            if(i>=59){
                // QUARTER FINAL
                matchRepository.save(new Match(EStatus.UNDEFINED,ERound.QUART_FINAL,type,tournament));
            }
            if(i>=61){
                // SEMI FINAL
                matchRepository.save(new Match(EStatus.UNDEFINED,ERound.SEMI_FINAL,type,tournament));
            }
            if(i>=62){
                // FINAL ROUND
                matchRepository.save(new Match(EStatus.UNDEFINED,ERound.FINAL_ROUND,type,tournament));
            }
        }

        return tournament;
    }

    @PostMapping("/tournaments")
    public Tournament postBody(
            @RequestBody @Valid TournamentRequest request) throws IOException {
        int typeSize = request.getTypes().size();

        int i, j;
        Tournament tournament = tournamentRepository.save(new Tournament(request.getYear()));
        for(j=0;j<typeSize;j++){
            EType type = null;
            switch (request.getTypes().get(j)) {
                case "SIMPLE_MEN":
                    type = EType.SIMPLE_MEN;
                    break;
                case "SIMPLE_WOMEN":
                    type = EType.SIMPLE_WOMEN;
                    break;
                case "DOUBLE_MEN":
                    type = EType.DOUBLE_MEN;
                    break;
                case "DOUBLE_WOMEN":
                    type = EType.DOUBLE_WOMAN;
                    break;
            }

            tournament = createMatches(tournament, type);
        }

        return tournament;
    }

    @GetMapping("/tournaments/{id}/players")
    public List<Person> players(
            @PathVariable Long id,
            @RequestParam(defaultValue = "") String type
    ) {
        EType etype = ETypeFormat.format(type);

        if (etype == null) {
            return personRepository.findByTournamentId(id);
        } else {
            return personRepository.findByTournamentIdAndType(id, etype);
        }
    }
}