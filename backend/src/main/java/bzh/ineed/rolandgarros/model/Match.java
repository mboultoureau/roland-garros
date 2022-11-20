package bzh.ineed.rolandgarros.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "matchs",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "id")
        })
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    // déclaration d'une table d'association
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;
    @Enumerated(EnumType.STRING)
    private EStatus status;
    @Enumerated(EnumType.STRING)
    private ERound round;

    @Enumerated(EnumType.STRING)
    private  EType type;

    private Date date;
    @ManyToOne
    // déclaration d'une table d'association
    @JoinTable(name = "teamA_match",
            joinColumns = @JoinColumn(name = "match_id"),
            inverseJoinColumns = @JoinColumn(name = "teamA_id"))
    private Team teamAId;

    @ManyToOne
    // déclaration d'une table d'association
    @JoinTable(name = "teamB_match",
            joinColumns = @JoinColumn(name = "match_id"),
            inverseJoinColumns = @JoinColumn(name = "teamB_id"))
    private Team teamBId;

    // CHANGER LE TYPE EN COURT
    private String CourtId;

    // --- CONSTRUCTOR ---
    public Match(Tournament tournament, EType type,EStatus status, ERound round) {
        this.tournament = tournament;
        this.type = type;
        this.status = status;
        this.round = round;
    }

    // constructeur temporaire
    public Match(String courtId) {
        CourtId = courtId;
    }

    public Match(Tournament tournament) {
        this.tournament = tournament;
    }

    public Match() {
    }

    // --- METHOD ---
    public EType getType() {
        return type;
    }

    public void setType(EType type) {
        this.type = type;
    }

    public String getCourtId() {
        return CourtId;
    }

    public void setCourtId(String courtId) {
        CourtId = courtId;
    }

    public void setTeamBId(Team teamBId) {
        this.teamBId = teamBId;
    }

    public Team getTeamBId() {
        return teamBId;
    }

    public void setTeamAId(Team teamAId) {
        this.teamAId = teamAId;
    }

    public Team getTeamAId() {
        return teamAId;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setRound(ERound round) {
        this.round = round;
    }

    public ERound getRound() {
        return round;
    }

    public void setStatus(EStatus status) {
        this.status = status;
    }

    public EStatus getStatus() {
        return status;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public Tournament getTournament(){
        return tournament;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
