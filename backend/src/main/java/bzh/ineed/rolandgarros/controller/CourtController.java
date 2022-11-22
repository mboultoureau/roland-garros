package bzh.ineed.rolandgarros.controller;


import bzh.ineed.rolandgarros.model.Court;
import bzh.ineed.rolandgarros.repository.CourtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CourtController {

    @Autowired
    CourtRepository courtRepository;

    @GetMapping("/courts")
    public List<Court> courts() {
        return courtRepository.findAll();
    }
}
