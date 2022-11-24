package bzh.ineed.rolandgarros.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "matches")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

    @Enumerated(EnumType.STRING)
    private EStatus status;

    @Enumerated(EnumType.STRING)
    private ERound round;

    @Enumerated(EnumType.STRING)
    private  EType type;

    private LocalDateTime startDate;

    private Integer duration;

    @ManyToOne
    private Team teamA;

    @ManyToOne
    private Team teamB;

    @ManyToOne
    private Court court;

    @Transient
    private Long courtId;

    @OneToMany(mappedBy = "match")
    // Circular reference
    @JsonIgnoreProperties("match")
    private Set<Score> scores;

    @ManyToOne
    private Team winner;

    public Match() {
    }

    public Match(EStatus status, ERound round, EType type, Tournament tournament) {
        this.status = status;
        this.round = round;
        this.type = type;
        this.tournament = tournament;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public EStatus getStatus() {
        return status;
    }

    public void setStatus(EStatus status) {
        this.status = status;
    }

    public ERound getRound() {
        return round;
    }

    public void setRound(ERound round) {
        this.round = round;
    }

    public EType getType() {
        return type;
    }

    public void setType(EType type) {
        this.type = type;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Team getTeamA() {
        return teamA;
    }

    public void setTeamA(Team teamA) {
        this.teamA = teamA;
    }

    public Team getTeamB() {
        return teamB;
    }

    public void setTeamB(Team teamB) {
        this.teamB = teamB;
    }

    public Court getCourt() {
        return court;
    }

    public void setCourt(Court court) {
        this.court = court;
    }

    public Long getCourtId() {
        return courtId;
    }

    public void setCourtId(Long courtId) {
        this.courtId = courtId;
    }

    public Set<Score> getScores() {
        return scores;
    }

    public void setScores(Set<Score> scores) {
        this.scores = scores;
    }

    public Team getWinner() {
        return winner;
    }

    public void setWinner(Team winner) {
        this.winner = winner;
    }

    @Override
    public String toString() {
        return "Status: " + status + ", Round: " + round + ", Type: " + type + ", Tournament year:" + tournament.getYear();
    }
}
