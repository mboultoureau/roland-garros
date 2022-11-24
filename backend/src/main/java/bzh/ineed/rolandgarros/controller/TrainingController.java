package bzh.ineed.rolandgarros.controller;


import bzh.ineed.rolandgarros.model.Role;
import bzh.ineed.rolandgarros.model.Training;
import bzh.ineed.rolandgarros.repository.CourtRepository;
import bzh.ineed.rolandgarros.repository.PersonRepository;
import bzh.ineed.rolandgarros.repository.RoleRepository;
import bzh.ineed.rolandgarros.repository.TrainingRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/training")
    public List<Training> trainings() {
        return trainingRepository.findAll();
    }

    @GetMapping("/training/{id}")
    public Training training(@PathVariable(value = "id") Long id) {
        return trainingRepository.findById(id).orElse(null);
    }

    @PostMapping("/training")
    public Training createTraining(@Valid @RequestBody Training training) {
        training.setCourt(courtRepository.findById(training.getCourtId()).orElse(null));
        training.setPlayer(personRepository.findById(training.getPlayerId()).orElse(null));

        return trainingRepository.save(training);
    }

    @PutMapping("/training/{id}")
    public Training updateTraining(@PathVariable(value = "id") Long id, @Valid @RequestBody Training training) {
        Training trainingToUpdate = trainingRepository.findById(id).orElse(null);
        trainingToUpdate.setCourt(courtRepository.findById(training.getCourtId()).orElse(null));
        trainingToUpdate.setPlayer(personRepository.findById(training.getPlayerId()).orElse(null));
        trainingToUpdate.setDetails(training.getDetails());
        trainingToUpdate.setDuration(training.getDuration());
        trainingToUpdate.setStartDate(training.getStartDate());

        return trainingRepository.save(trainingToUpdate);
    }

    @DeleteMapping("/training/{id}")
    public void deleteTraining(@PathVariable(value = "id") Long id) {
        trainingRepository.deleteById(id);
    }
}
