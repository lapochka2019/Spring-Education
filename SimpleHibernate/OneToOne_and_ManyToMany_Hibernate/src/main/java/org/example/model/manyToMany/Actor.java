package org.example.model.manyToMany;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import java.util.List;
import org.example.model.manyToMany.Movie;

@Entity
@Table(name = "Actor")
public class Actor {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private int age;
    @ManyToMany
    @JoinTable(name="Actor_and_Movie",
            joinColumns =@JoinColumn(name = "actor_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id"))
    private List<Movie> movies;

    public Actor(){
    }

    public Actor(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int actor_id) {
        this.id = actor_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public String toString() {
        return name +" (" + age + " years)";
    }
}
