package org.example;

import org.example.model.manyToMany.Actor;
import org.example.model.manyToMany.Movie;
import org.example.model.oneToOne.Passport;
import org.example.model.oneToOne.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ){
        //Configuration configuration = new Configuration().addAnnotatedClass(Person.class).addAnnotatedClass(Passport.class);
        Configuration configuration = new Configuration().addAnnotatedClass(Actor.class).addAnnotatedClass(Movie.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        //try with resources
        //Java 9
        try(sessionFactory){
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            //Person person = new Person("First Person", 14);
            //Passport passport = new Passport(person, 123456);
            //person.setPassport(passport);
            //session.save(person);

            //Movie movie = new Movie("Pulp fiction", 1998);
            //Actor actor1 = new Actor("Harvey Keitel", 81);
            //Actor actor2 = new Actor("Samuel L. Jackson",72);

            //movie.setActors(new ArrayList<>(List.of(actor1,actor2)));
            //actor1.setMovies(new ArrayList<>(Collections.singletonList(movie)));
            //actor2.setMovies(new ArrayList<>(Collections.singletonList(movie)));

            //session.save(movie);
            //session.save(actor1);
            //session.save(actor2);

            Movie movie = session.get(Movie.class,1);
            System.out.println(movie.getActors());

            session.getTransaction().commit();

        }
    }
}
