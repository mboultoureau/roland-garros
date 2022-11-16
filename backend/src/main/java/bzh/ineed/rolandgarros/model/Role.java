package bzh.ineed.rolandgarros.model;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;

    public Role() {}

    public Role(ERole name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ERole getName() {
        return name;
    }

    public void setName(ERole name) {
        this.name = name;
    }

    @Override
    public String toString() {
        String result = "";
        switch (this.getName()) {
            case ROLE_ADMIN -> result = "ROLE_ADMIN";
            case ROLE_USER -> result = "ROLE_USER";
            case ROLE_EDITOR_MATCH -> result = "ROLE_EDITOR_MATCH";
            case ROLE_EDITOR_PLAYER -> result = "ROLE_EDITOR_PLAYER";
        }

        return result;
    }
}
