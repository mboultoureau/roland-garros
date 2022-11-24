package bzh.ineed.rolandgarros.controller;

import bzh.ineed.rolandgarros.model.Person;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/api")
public class StatisticController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/statistics")
    public ResponseEntity<?> statistics() {
        HashMap<String, Object> response = new HashMap<>();

        // Get best players
        ArrayList<HashMap<String, Object>> bestPlayers = new ArrayList<>();

        jdbcTemplate.query("SELECT COUNT(*) AS victories, persons.id, persons.firstname, persons.lastname, persons.picture, countries.alpha3code FROM persons\n" +
                "INNER JOIN teams ON persons.id = teams.persona_id OR persons.id = teams.personb_id\n" +
                "INNER JOIN matches ON teams.id = matches.winner_id\n" +
                "INNER JOIN countries ON persons.nationality_id = countries.id\n" +
                "WHERE persons.is_player = TRUE\n" +
                "GROUP BY persons.id\n" +
                "ORDER BY victories DESC\n" +
                "LIMIT 10;", (rs, rowNum) -> {
                HashMap<String, Object> player = new HashMap<>();
                player.put("id", rs.getInt("id"));
                player.put("firstname", rs.getString("firstname"));
                player.put("lastname", rs.getString("lastname"));
                player.put("picture", rs.getString("picture"));
                player.put("alpha3code", rs.getString("alpha3code"));
                player.put("victories", rs.getInt("victories"));
                bestPlayers.add(player);
                    return new Object[]{rs.getInt("victories"), rs.getInt("id"), rs.getString("firstname"), rs.getString("lastname")};
                }
        );

        response.put("bestPlayers", bestPlayers);

        // Get players with most duration
        ArrayList<HashMap<String, Object>> bestDurations = new ArrayList<>();

        jdbcTemplate.query("SELECT SUM(matches.duration) as duration, persons.id, persons.firstname, persons.lastname, persons.picture, countries.alpha3code FROM persons\n" +
                "INNER JOIN teams ON teams.persona_id = persons.id OR teams.personb_id = persons.id\n" +
                "INNER JOIN matches ON teams.id = matches.teama_id OR teams.id = matches.teamb_id\n" +
                "INNER JOIN countries ON countries.id = persons.nationality_id\n" +
                "WHERE persons.is_player = TRUE\n" +
                "GROUP BY persons.id\n" +
                "ORDER BY duration DESC\n" +
                "LIMIT 10;", (rs, rowNum) -> {
                HashMap<String, Object> player = new HashMap<>();
                player.put("id", rs.getInt("id"));
                player.put("firstname", rs.getString("firstname"));
                player.put("lastname", rs.getString("lastname"));
                player.put("picture", rs.getString("picture"));
                player.put("alpha3code", rs.getString("alpha3code"));
                player.put("duration", rs.getInt("duration"));
                bestDurations.add(player);
                    return new Object[]{rs.getInt("duration"), rs.getInt("id"), rs.getString("firstname"), rs.getString("lastname")};
                }
        );

        response.put("bestDurations", bestDurations);

        return ResponseEntity.ok(response);
    }
}
