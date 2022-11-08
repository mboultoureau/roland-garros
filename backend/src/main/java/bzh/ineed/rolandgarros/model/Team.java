package bzh.ineed.rolandgarros.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;

@Entity
@Table(name = "equipe")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private Player player1;

    private Player player2;

    // Equipe d'un seul team
    public Team(Player player){
        this.player = player1;
    }

    // Equipe de 2 teams
    public Team(Team team1, Team team2){
        this.team1 = team1;
        this.team2 = team2;
    }

    @ManyToMany
    @JoinTable(name = "equipe_match",
            joinColumns = @JoinColumn(name = "equipe_id"),
            inverseJoinColumns = @JoinColumn(name = "match_id"))
    private Set<Match> matchSet = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "equipe_team",
            joinColumns = @JoinColumn(name = "equipe_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id"))
    private Set<Team> teamSet = new HashSet<>();


    public Integer getId() {
        return id;
    }

    public team getteam1() {
        return team1;
    }

    public team getteam2() {
        return team2;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }
}