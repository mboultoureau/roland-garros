package bzh.ineed.rolandgarros.model;

import jakarta.persistence.*;

@Entity
@Table(name = "teams",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "id")
        })
public class Team {
    // CHANGER LE TYPE EN PERSON
    private String person1;

    // CHANGER LE TYPE EN PERSON
    private String person2;
    private Long id;

    public void setPerson2(String person2) {
        this.person2 = person2;
    }

    public String getPerson2() {
        return person2;
    }

    public void setPerson1(String person1) {
        this.person1 = person1;
    }

    public String getPerson1() {
        return person1;
    }


    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }
}