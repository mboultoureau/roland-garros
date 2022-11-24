package bzh.ineed.rolandgarros.controller;


import bzh.ineed.rolandgarros.exception.BadRequestException;
import bzh.ineed.rolandgarros.model.Court;
import bzh.ineed.rolandgarros.model.Match;
import bzh.ineed.rolandgarros.model.Role;
import bzh.ineed.rolandgarros.model.Training;
import bzh.ineed.rolandgarros.repository.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TrainingController {

    @Autowired
    TrainingRepository trainingRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    CourtRepository courtRepository;

    @Autowired
    MatchRepository matchRepository;

    @GetMapping("/trainings")
    public List<Training> trainings() {
        return trainingRepository.findAll();
    }

    @GetMapping("/trainings/{id}")
    public Training training(@PathVariable(value = "id") Long id) {
        return trainingRepository.findById(id).orElse(null);
    }

    public Boolean checkIfCourtAvailable(LocalDateTime startDate, Integer duration, Court court) {
        LocalDateTime endDate = startDate.plusHours(duration);
        List<Training> trainings = trainingRepository.findByCourtAndStartDateBetween(
                court,
                startDate.withHour(0).withMinute(0).withSecond(0),
                startDate.withHour(23).withMinute(59).withSecond(59)
        );

        for (Training training : trainings) {
            if (training.getStartDate().isBefore(endDate) && training.getStartDate().plusHours(training.getDuration()).isAfter(startDate)) {
                return false;
            }
        }

        List<Match> matches = matchRepository.findAllByCourtAndStartDateBetween(
                court,
                startDate.withHour(0).withMinute(0).withSecond(0),
                startDate.withHour(23).withMinute(59).withSecond(59)
        );

        for (Match match : matches) {
            if (match.getStartDate().isBefore(endDate) && match.getStartDate().plusHours(match.getDuration()).isAfter(startDate)) {
                return false;
            }
        }

        return true;
    }

    @PostMapping("/trainings")
    public Training createTraining(@Valid @RequestBody Training training) {
        if (training.getCourtId() != null && courtRepository.existsById(training.getCourtId())) {
            training.setCourt(courtRepository.findById(training.getCourtId()).orElse(null));
        }
        if (training.getPlayerId() != null && personRepository.existsById(training.getPlayerId())) {
            training.setPlayer(personRepository.findById(training.getPlayerId()).orElse(null));
        }

        if (training.getCourt() != null && training.getStartDate() != null && training.getDuration() != null) {
            if (checkIfCourtAvailable(training.getStartDate(), training.getDuration(), training.getCourt())) {
                return trainingRepository.save(training);
            } else {
                throw new BadRequestException("Court not available");
            }
        } else {
            training.setCourt(null);
            training.setDuration(null);
            training.setStartDate(null);
        }

        return trainingRepository.save(training);
    }

    @PutMapping("/trainings/{id}")
    public Training updateTraining(@PathVariable(value = "id") Long id, @Valid @RequestBody Training training) {
        Training trainingToUpdate = trainingRepository.findById(id).orElse(null);

        if (training.getCourtId() != null && courtRepository.existsById(training.getCourtId())) {
            training.setCourt(courtRepository.findById(training.getCourtId()).orElse(null));
        }
        if (training.getPlayerId() != null && personRepository.existsById(training.getPlayerId())) {
            training.setPlayer(personRepository.findById(training.getPlayerId()).orElse(null));
        }

        trainingToUpdate.setDetails(training.getDetails());
        trainingToUpdate.setDuration(training.getDuration());
        trainingToUpdate.setStartDate(training.getStartDate());

        if (training.getCourt() != null && training.getStartDate() != null && training.getDuration() != null) {
            if (checkIfCourtAvailable(training.getStartDate(), training.getDuration(), training.getCourt())) {
                return trainingRepository.save(training);
            } else {
                throw new BadRequestException("Court not available");
            }
        } else {
            trainingToUpdate.setCourt(null);
            trainingToUpdate.setDuration(null);
            trainingToUpdate.setStartDate(null);
        }

        return trainingRepository.save(trainingToUpdate);
    }

    @DeleteMapping("/trainings/{id}")
    public void deleteTraining(@PathVariable(value = "id") Long id) {
        trainingRepository.deleteById(id);
    }
}
