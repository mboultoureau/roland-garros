package bzh.ineed.rolandgarros.model;

import jakarta.persistence.*;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ESex gender;

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

    public Person(String sex, String firstname, String lastname){
        this.sex = sex;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Integer getId() {
        return id;
    }

    public ESex getGender() {
        return gender;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public Date getbirthDate() {
        return birthDate;
    }

    public String getbirthPlace() {
        return birthPlace;
    }

    public Integer getWeight() {
        return weight;
    }

    public Integer getHeight() {
        return height;
    }

    public void setGender(ESex gender) {
        this.gender = gender;
    }

    public void setbirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setbirthPlace(String birthPlace) {
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

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}