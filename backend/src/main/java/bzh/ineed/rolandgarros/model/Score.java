package bzh.ineed.rolandgarros.model;

import jakarta.persistence.*;

@Entity
@Table(name = "scores")
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer round;

    private Integer scoreTeamA;

    private Integer scoreTeamB;

    @ManyToOne
    private Match match;

    public Score() {}

    public Score(Integer round, Integer scoreTeamA, Integer scoreTeamB, Match match) {
        this.round = round;
        this.scoreTeamA = scoreTeamA;
        this.scoreTeamB = scoreTeamB;
        this.match = match;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRound() {
        return round;
    }

    public void setRound(Integer round) {
        this.round = round;
    }

    public Integer getScoreTeamA() {
        return scoreTeamA;
    }

    public void setScoreTeamA(Integer scoreTeamA) {
        this.scoreTeamA = scoreTeamA;
    }

    public Integer getScoreTeamB() {
        return scoreTeamB;
    }

    public void setScoreTeamB(Integer scoreTeamB) {
        this.scoreTeamB = scoreTeamB;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }
}
