package bzh.ineed.rolandgarros.controller;

import bzh.ineed.rolandgarros.exception.BadRequestException;
import bzh.ineed.rolandgarros.exception.NotFoundException;
import bzh.ineed.rolandgarros.model.*;
import bzh.ineed.rolandgarros.model.Team;
import bzh.ineed.rolandgarros.repository.*;
import bzh.ineed.rolandgarros.util.ERoundFormat;
import bzh.ineed.rolandgarros.util.ETypeFormat;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MatchController {
    @Autowired
    MatchRepository matchRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    CourtRepository courtRepository;

    @Autowired
    ScoreRepository scoreRepository;

    @Autowired
    TrainingRepository trainingRepository;

    @GetMapping("/matches")
    public Page<Match> index(
            @ParameterObject Pageable pageable,
            @RequestParam(defaultValue = "") String round,
            @RequestParam(defaultValue = "") String type,
            @RequestParam(defaultValue = "") Long tournamentId
    ) {
        EType etype = ETypeFormat.format(type);
        ERound eround = ERoundFormat.format(round);

        if (etype != null && eround != null && tournamentId != null) {
            return matchRepository.findByRoundAndTypeAndTournamentId(eround, etype, tournamentId, pageable);
        } else {
            return matchRepository.findAll(pageable);
        }
    }

    public Match addTeamA(Match actualMatch, Match newMatch) {
        Boolean isDouble = actualMatch.getType() != null &&
                (actualMatch.getType().equals(EType.DOUBLE_MEN) ||
                actualMatch.getType().equals(EType.DOUBLE_WOMAN) ||
                actualMatch.getType().equals(EType.MIXED));;

        // Check if all data are correct
        if (newMatch.getTeamA() == null) {
            return actualMatch;
        }

        if (newMatch.getTeamA().getPersonAId() == null || newMatch.getTeamA().getPersonAId().equals("null")) {
            return actualMatch;
        }

        if (isDouble && (newMatch.getTeamA().getPersonBId() == null || newMatch.getTeamA().getPersonBId().equals("null"))) {
            return actualMatch;
        }

        Long person1Id = Long.parseLong(newMatch.getTeamA().getPersonAId());
        Person person1 = null;
        Person person2 = null;

        if (!personRepository.existsById(person1Id)) {
            System.out.println("Player1 doesn't exists");
            return actualMatch;
        }

        person1 = personRepository.findById(person1Id).get();

        if (isDouble) {
            Long person2Id = Long.parseLong(newMatch.getTeamA().getPersonBId());
            if (!personRepository.existsById(person2Id)) {
                System.out.println("Player2 doesn't exists");
                return actualMatch;
            }
            person2 = personRepository.findById(person2Id).get();
        }

        // Remove old team
        if (actualMatch.getTeamA() != null) {
            Team teamToDelete = actualMatch.getTeamA();
            actualMatch.setTeamA(null);
            matchRepository.save(actualMatch);
            teamRepository.delete(teamToDelete);
        }

        // Create new team
        Team team = new Team();
        team.setPersonA(person1);

        if (isDouble) {
            team.setPersonB(person2);
        }

        teamRepository.save(team);
        actualMatch.setTeamA(team);

        return actualMatch;
    }

    public Match updateScores(Match actualMatch, Match newMatch) {
        if (newMatch.getScores() == null) {
            return actualMatch;
        }

        for (Score score : newMatch.getScores()) {
            if (score.getRound() == null) {
                continue;
            }

            if (score.getScoreTeamA() == null) {
                continue;
            }

            if (score.getScoreTeamB() == null) {
                continue;
            }

            Score actualScore = actualMatch.getScores().stream()
                    .filter(s -> s.getRound().equals(score.getRound()))
                    .findFirst()
                    .orElse(null);

            if (actualScore == null) {
                actualScore = new Score();
                actualScore.setRound(score.getRound());
                actualScore.setMatch(actualMatch);
                actualMatch.getScores().add(actualScore);
            }

            actualScore.setScoreTeamA(score.getScoreTeamA());
            actualScore.setScoreTeamB(score.getScoreTeamB());

            scoreRepository.save(actualScore);
        }

        return actualMatch;
    }

    public Match addTeamB(Match actualMatch, Match newMatch) {
        Boolean isDouble = actualMatch.getType() != null &&
                (actualMatch.getType().equals(EType.DOUBLE_MEN) ||
                actualMatch.getType().equals(EType.DOUBLE_WOMAN) ||
                actualMatch.getType().equals(EType.MIXED));;

        // Check if all data are correct
        if (newMatch.getTeamB() == null) {
            return actualMatch;
        }

        if (newMatch.getTeamB().getPersonAId() == null || newMatch.getTeamB().getPersonAId().equals("null")) {
            return actualMatch;
        }

        if (isDouble && (newMatch.getTeamB().getPersonBId() == null || newMatch.getTeamB().getPersonBId().equals("null"))) {
            return actualMatch;
        }

        Long person1Id = Long.parseLong(newMatch.getTeamB().getPersonAId());
        Person person1 = null;
        Person person2 = null;

        if (!personRepository.existsById(person1Id)) {
            System.out.println("Player1 doesn't exists");
            return actualMatch;
        }

        person1 = personRepository.findById(person1Id).get();

        if (isDouble) {
            Long person2Id = Long.parseLong(newMatch.getTeamB().getPersonBId());
            if (!personRepository.existsById(person2Id)) {
                System.out.println("Player2 doesn't exists");
                return actualMatch;
            }
            person2 = personRepository.findById(person2Id).get();
        }

        // Remove old team
        if (actualMatch.getTeamB() != null) {
            Team teamToDelete = actualMatch.getTeamB();
            actualMatch.setTeamB(null);
            matchRepository.save(actualMatch);
            teamRepository.delete(teamToDelete);
        }

        // Create new team
        Team team = new Team();
        team.setPersonA(person1);

        if (isDouble) {
            team.setPersonB(person2);
        }

        teamRepository.save(team);
        actualMatch.setTeamB(team);

        return actualMatch;
    }

    @PutMapping("/matches/{id}")
    Match replace(@RequestBody Match newMatch, @PathVariable Long id) {
        return matchRepository.findById(id)
                .map(match -> {
                    match.setStartDate(newMatch.getStartDate());
                    match.setDuration(newMatch.getDuration());
                    match = addTeamA(match, newMatch);
                    match = addTeamB(match, newMatch);
                    match.setStatus(newMatch.getStatus());
                    match.setRound(newMatch.getRound());
                    match.setType(newMatch.getType());
                    match = updateScores(match, newMatch);

                    if (newMatch.getCourtId() != null) {
                        if (courtRepository.existsById(newMatch.getCourtId())) {
                            match.setCourt(courtRepository.findById(newMatch.getCourtId()).get());
                        }
                    }

                    if (match.getCourtId() != null && courtRepository.existsById(match.getCourtId())) {
                        match.setCourt(courtRepository.findById(match.getCourtId()).get());
                    }

                    // Check if time slot is free on the same court
                    if (match.getCourt() != null && match.getStartDate() != null && match.getDuration() != null) {
                        List<Match> matches = matchRepository.findAllByCourtAndStartDateBetween(
                                match.getCourt(),
                                match.getStartDate().withHour(0).withMinute(0).withSecond(0),
                                match.getStartDate().withHour(23).withMinute(59).withSecond(59)
                        );

                        List<Training> trainings = trainingRepository.findAllByCourtAndStartDateBetween(
                                match.getCourt(),
                                match.getStartDate().withHour(0).withMinute(0).withSecond(0),
                                match.getStartDate().withHour(23).withMinute(59).withSecond(59)
                        );

                        // Check if match is in the same time slot
                        for (Match m : matches) {
                            if (m.getId().equals(match.getId())) {
                                continue;
                            }

                            if (m.getStartDate().isBefore(match.getStartDate().plusHours(match.getDuration())) &&
                                    m.getStartDate().plusHours(m.getDuration()).isAfter(match.getStartDate())) {
                                throw new BadRequestException("Time slot is not free");
                            }
                        }

                        // Check if training is in the same time slot
                        for (Training t : trainings) {
                            if (t.getStartDate().isBefore(match.getStartDate().plusHours(match.getDuration())) &&
                                    t.getStartDate().plusHours(t.getDuration()).isAfter(match.getStartDate())) {
                                throw new BadRequestException("Time slot is not free");
                            }
                        }
                    }


                    return matchRepository.save(match);
                })
                .orElseGet(() -> {
                    newMatch.setId(id);
                    return matchRepository.save(newMatch);
                });
    }

    @GetMapping("/matches/{id}")
    public Match getMatch(@PathVariable Long id) {
        return matchRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Could not find match " + id));
    }

}
