package bzh.ineed.rolandgarros.model;

import jakarta.persistence.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "persons")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EGender gender;

    @ManyToOne
    private Country nationality;

    @Transient
    private Long nationalityId;

    @NotBlank
    @Size(max = 50)
    private String firstname;

    @NotBlank
    @Size(max = 50)
    private String lastname;

    private LocalDate birthDate;

    private String birthPlace;

    private Integer weight;

    private Integer height;

    private String picture;

    private boolean isPlayer;

    private boolean isCoach;

    @ManyToOne
    private Person coach;

    @Transient
    private Long coachId;

    private Integer ranking;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EHand hand;

    private LocalDate earlyCareer;

    public Person() {}

    public Person(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EGender getGender() {
        return gender;
    }

    public void setGender(EGender gender) {
        this.gender = gender;
    }

    public Country getNationality() {
        return nationality;
    }

    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }

    public Long getNationalityId() {
        return nationalityId;
    }

    public void setNationalityId(Long nationalityId) {
        this.nationalityId = nationalityId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public boolean getIsPlayer() {
        return isPlayer;
    }

    public void setIsPlayer(boolean player) {
        isPlayer = player;
    }

    public boolean getIsCoach() {
        return isCoach;
    }

    public void setIsCoach(boolean coach) {
        isCoach = coach;
    }

    public Person getCoach() {
        return coach;
    }

    public void setCoach(Person coach) {
        this.coach = coach;
    }

    public Long getCoachId() {
        return coachId;
    }

    public void setCoachId(Long coachId) {
        this.coachId = coachId;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public EHand getHand() {
        return hand;
    }

    public void setHand(EHand hand) {
        this.hand = hand;
    }

    public LocalDate getEarlyCareer() {
        return earlyCareer;
    }

    public void setEarlyCareer(LocalDate earlyCareer) {
        this.earlyCareer = earlyCareer;
    }

    @Override
    public String toString() {
        return this.firstname + " " + this.lastname;
    }
}