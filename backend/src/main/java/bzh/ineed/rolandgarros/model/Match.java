package bzh.ineed.rolandgarros.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;

@Entity
@Table(name = "match",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "id")
        })
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 20)
    private EStatus status;

    @NotBlank
    @Size(max = 50)
    private EPhase phase;

    @NotBlank
    private Date date;

    @NotBlank
    private EType type;

    @NotBlank
    private Equipe equipe1;

    @NotBlank
    private Equipe equipe2;

    @NotBlank
    private Score score;

    @NotBlank
    @Size(max = 50)
    private String court_id;


    @OneToOne
    @JoinTable(name = "match_status",
            joinColumns = @JoinColumn(name = "match_id"),
            inverseJoinColumns = @JoinColumn(name = "status_id"))
    private Set<EStatus> roles = new HashSet<>();

    @OneToOne
    @JoinTable(name = "match_phase",
            joinColumns = @JoinColumn(name = "match_id"),
            inverseJoinColumns = @JoinColumn(name = "phase_id"))
    private Set<Phase> roles = new HashSet<>();

    @OneToOne
    @JoinTable(name = "match_type",
            joinColumns = @JoinColumn(name = "match_id"),
            inverseJoinColumns = @JoinColumn(name = "type_id"))
    private Set<Type> roles = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "match_equipe",
            joinColumns = @JoinColumn(name = "match_id"),
            inverseJoinColumns = @JoinColumn(name = "equipe_id"))
    private Set<Equipe> equipeSet = new HashSet<>();

    @OneToMany
    @JoinTable(name = "match_score",
            joinColumns = @JoinColumn(name = "match_id"),
            inverseJoinColumns = @JoinColumn(name = "score_id"))
    private Set<Score> roles = new HashSet<>();

    @OneToMany
    @JoinTable(name = "match_court",
            joinColumns = @JoinColumn(name = "match_id"),
            inverseJoinColumns = @JoinColumn(name = "court_id"))
    private Set<Court> roles = new HashSet<>();

/*
    public Match() {

    }

    public Match(EStatus status, EPhase phase, EType type, Equipe equipe1, Equipe equipe2, Score score, Court court) {

    }

 */


}
