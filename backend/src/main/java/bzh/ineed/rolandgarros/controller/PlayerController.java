package bzh.ineed.rolandgarros.controller;

import bzh.ineed.rolandgarros.model.EGender;
import bzh.ineed.rolandgarros.model.Player;
import bzh.ineed.rolandgarros.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PlayerController {

    @Autowired
    PlayerRepository playerRepository;

    @GetMapping("/players")
    public ResponseEntity<?> index(
            @RequestParam(defaultValue = "") String gender
    ) {
        List<Player> players = new ArrayList<Player>();

        if (gender.equals("woman")) {
            players = playerRepository.findByGender(EGender.WOMAN, null);
        } else if (gender.equals("man")) {
            players = playerRepository.findByGender(EGender.MAN, null);
        } else {
            players = playerRepository.findAll();
        }

        return ResponseEntity.ok().body(players);
    }



}
