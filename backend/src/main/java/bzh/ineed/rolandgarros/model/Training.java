package bzh.ineed.rolandgarros.model;

import jakarta.persistence.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Table(name = "trainings")
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @Size(max = 250)
    private String details;

    @NotBlank
    private LocalDate date;

    @NotBlank
    private Timestamp start;

    private int court_id;

    @NotBlank
    private int nbHours;

    @ManyToOne
    private Training coach;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Training() {}

}