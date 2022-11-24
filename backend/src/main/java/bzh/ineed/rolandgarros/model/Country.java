package bzh.ineed.rolandgarros.model;

import jakarta.persistence.*;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "countries",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "name"),
                @UniqueConstraint(columnNames = "alpha2Code"),
                @UniqueConstraint(columnNames = "alpha3Code")
        })
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Length(min = 2, max = 100)
    private String name;

    @Length(min = 2, max = 2)
    private String alpha2Code;

    @Length(min = 3, max = 3)
    private String alpha3Code;

    public Country() {}

    public Country(String name, String alpha2Code, String alpha3Code) {
        this.name = name;
        this.alpha2Code = alpha2Code;
        this.alpha3Code = alpha3Code;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlpha2Code() {
        return alpha2Code;
    }

    public void setAlpha2Code(String alpha2Code) {
        this.alpha2Code = alpha2Code;
    }

    public String getAlpha3Code() {
        return alpha3Code;
    }

    public void setAlpha3Code(String alpha3Code) {
        this.alpha3Code = alpha3Code;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
