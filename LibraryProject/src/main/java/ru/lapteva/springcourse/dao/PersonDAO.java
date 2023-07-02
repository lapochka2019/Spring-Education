package ru.lapteva.springcourse.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.lapteva.springcourse.models.Book;
import ru.lapteva.springcourse.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    //@Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //получить всех персон
    public List<Person> index (){
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }
    //создание нового пользователя
    public void create(Person person){
        jdbcTemplate.update("INSERT INTO Person(name,year) values (?,?)", person.getName(), person.getYear());
    }
    //редактирования данных пользователя
    public void update(int id, Person person){
        jdbcTemplate.update("Update person set name=?, year=? where id=?", person.getName(), person.getYear(), id);
    }
    //удаление пользователя
    public void del (int id){
        jdbcTemplate.update("delete from person where id=?", id);
    }
    //получить конкретного пользователя
    public Person getPerson(int id){
        return jdbcTemplate.query("Select * from person where id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }
    //получить книги пользователя
    public List <Book> getBooksByPersonId(int id){
        return jdbcTemplate.query("select * from books where person_id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class));
    }
    //для валидации
    //(не) получаем пользователя с таким же ФИО
    public Optional<Person> isUniq(String name){
        return jdbcTemplate.query("select * from Person where name=?", new Object[]{name}, new BeanPropertyRowMapper(Person.class)).stream().findAny();
    }
}
