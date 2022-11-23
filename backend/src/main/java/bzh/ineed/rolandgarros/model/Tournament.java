package bzh.ineed.rolandgarros.model;


import jakarta.persistence.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Entity
@Table(name = "tournaments",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "year")
        })
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer year;

    public Tournament(){

    }
    public Tournament(Integer year) {
        this.year = year;
    }

    // Getter and setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return this.year.toString();
    }
}
