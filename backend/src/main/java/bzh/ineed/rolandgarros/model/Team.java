package bzh.ineed.rolandgarros.model;

import jakarta.persistence.*;

@Entity
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Person personA;

    @ManyToOne
    private Person personB;

    @Transient
    private String personAId;

    @Transient String personBId;

    public Team() {}

    public Team(Person personA, Person personB) {
        this.personA = personA;
        this.personB = personB;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getPersonA() {
        return personA;
    }

    public void setPersonA(Person person1) {
        this.personA = person1;
    }

    public Person getPersonB() {
        return personB;
    }

    public void setPersonB(Person person2) {
        this.personB = person2;
    }

    public String getPersonAId() {
        return personAId;
    }

    public void setPersonAId(String personAId) {
        this.personAId = personAId;
    }

    public String getPersonBId() {
        return personBId;
    }

    public void setPersonBId(String personBId) {
        this.personBId = personBId;
    }
}