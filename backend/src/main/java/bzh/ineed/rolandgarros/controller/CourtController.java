package bzh.ineed.rolandgarros.controller;


import bzh.ineed.rolandgarros.exception.BadRequestException;
import bzh.ineed.rolandgarros.model.Court;
import bzh.ineed.rolandgarros.repository.CourtRepository;
import bzh.ineed.rolandgarros.repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CourtController {

    @Autowired
    CourtRepository courtRepository;

    @Autowired
    TrainingRepository trainingRepository;

    @GetMapping("/courts")
    public ResponseEntity courts(
            @RequestParam(defaultValue = "") String startWeek
    ) {
        if (!startWeek.equals("")) {
            try {
                LocalDate mondayDate = LocalDate.parse(startWeek);
                LocalDateTime startDate = mondayDate.atStartOfDay();
                LocalDateTime endDate = startDate.plusDays(7).minusSeconds(1);

                List<Court> courts = courtRepository.findAll();

                ArrayList<Map<String, Object>> response = new ArrayList<>();

                for (Court court : courts) {
                    Map<String, Object> r = new HashMap<>();
                    r.put("id", court.getId());
                    r.put("name", court.getName());
                    r.put("trainings", trainingRepository.findByCourtAndStartDateBetween(court, startDate, endDate));

                    response.add(r);
                }


                return ResponseEntity.ok().body(response);
            } catch (DateTimeParseException e) {
                throw new BadRequestException("Start week is not a valid date");
            }
        } else {
            return ResponseEntity.ok().body(courtRepository.findAll());
        }
    }
}
