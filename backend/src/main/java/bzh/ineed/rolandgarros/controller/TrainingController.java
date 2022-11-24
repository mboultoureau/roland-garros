package bzh.ineed.rolandgarros.controller;


import bzh.ineed.rolandgarros.model.Role;
import bzh.ineed.rolandgarros.model.Training;
import bzh.ineed.rolandgarros.repository.RoleRepository;
import bzh.ineed.rolandgarros.repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TrainingController {

    @Autowired
    TrainingRepository trainingRepository;

    @GetMapping("/training")
    public List<Training> trainings() {
        return trainingRepository.findAll();
    }
}
