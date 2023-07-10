package org.example.model.manyToMany;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Movie")
public class Movie {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "year_of_production")
    private int year_of_production;

    @ManyToMany(mappedBy = "movies")
    private List<Actor> actors;

    public Movie() {
    }

    public Movie(String title, int year_of_production) {
        this.title = title;
        this.year_of_production = year_of_production;
    }

    public int getId() {
        return id;
    }

    public void setId(int movie_id) {
        this.id = movie_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear_of_production() {
        return year_of_production;
    }

    public void setYear_of_production(int year_of_production) {
        this.year_of_production = year_of_production;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movie_id=" + id +
                ", title='" + title + '\'' +
                ", year_of_production=" + year_of_production +
                '}';
    }
}
