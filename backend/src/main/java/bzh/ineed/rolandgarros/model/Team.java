package bzh.ineed.rolandgarros.model;

import jakarta.persistence.*;

@Entity
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Person person1;

    @ManyToOne
    private Person person2;

    @Transient
    private String person1Id;

    @Transient String person2Id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getPerson1() {
        return person1;
    }

    public void setPerson1(Person person1) {
        this.person1 = person1;
    }

    public Person getPerson2() {
        return person2;
    }

    public void setPerson2(Person person2) {
        this.person2 = person2;
    }

    public String getPerson1Id() {
        return person1Id;
    }

    public void setPerson1Id(String person1Id) {
        this.person1Id = person1Id;
    }

    public String getPerson2Id() {
        return person2Id;
    }

    public void setPerson2Id(String person2Id) {
        this.person2Id = person2Id;
    }
}