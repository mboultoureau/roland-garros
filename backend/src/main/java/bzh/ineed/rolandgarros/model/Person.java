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
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ESex sex;

    @NotBlank
    @Size(max = 50)
    private String firstname;

    @NotBlank
    @Size(max = 50)
    private String lastname;

    @NotBlank
    @Size(max = 50)
    private Date birthDate;

    @NotBlank
    @Size(max = 50)
    private String birthPlace;

    @NotBlank
    private Integer weight;

    @NotBlank
    private Integer height;

    public Person(ESex sex, String firstname, String lastname){
        this.sex = sex;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    @OneToOne
    @JoinTable(name = "person_user",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> userSet = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "person_nationality",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "nationality_id"))
    private Set<Nationality> nationalitySet = new HashSet<>();

    @ManyToOne
    @JoinTable(name = "person_sex",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "sex_id"))
    private Set<ESex> sexSet = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public ESex getSex() {
        return sex;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public Integer getWeight() {
        return weight;
    }

    public Integer getHeight() {
        return height;
    }

    public void setSex(ESex sex) {
        this.sex = sex;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}