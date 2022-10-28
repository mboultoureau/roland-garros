package bzh.ineed.rolandgarros.model;

import jakarta.persistence.*;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(max = 20)
    private String sex;

    @NotBlank
    @Size(max = 50)
    private String firstname;

    @NotBlank
    @Size(max = 50)
    private String lastname;

    @NotBlank
    @Size(max = 50)
    private Date birth_date;

    @NotBlank
    @Size(max = 50)
    private String birth_place;

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

    public String getSex() {
        return sex;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public String getBirth_place() {
        return birth_place;
    }

    public Integer getWeight() {
        return weight;
    }

    public Integer getHeight() {
        return height;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public void setBirth_place(String birth_place) {
        this.birth_place = birth_place;
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