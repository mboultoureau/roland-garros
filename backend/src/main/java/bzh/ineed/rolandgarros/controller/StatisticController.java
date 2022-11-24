package bzh.ineed.rolandgarros.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class StatisticController {
    @GetMapping("/statistics")
    public ResponseEntity<?> statistics() {
        ResponseEntity<?> response = null;



        return response;
    }
}
