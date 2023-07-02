package ru.lapteva.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.lapteva.springcourse.models.Book;
import ru.lapteva.springcourse.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class BooksDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public BooksDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //создать книгу
    public void create (Book book){
        jdbcTemplate.update("insert into books (title, author,year) values (?,?,?)", book.getTitle(),book.getAuthor(), book.getYear());
    }
    //изменить книгу
    public void update (Book book, int id){
        jdbcTemplate.update("update books set title=?,author=?, year=? where id=?", book.getTitle(),book.getAuthor(), book.getYear(), id);
    }
    //удалить книгу
    public void del (int id){
        jdbcTemplate.update("delete  from books where id =?", id);
    }
    //показать все книги
    public List<Book> show (){
        return jdbcTemplate.query( "select * from books", new BeanPropertyRowMapper<>(Book.class));
    }
    //показать книгу по индексу
    public Book getBook (int id){
        return jdbcTemplate.query("select * from books where id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }
    //освобождаем книгу
    public void bookIsFree(int id){
        jdbcTemplate.update("update books set person_id =null where id=?",id);
    }
    //занимаем книгу
    public void bookIsOccupied(int id, Person person){
        jdbcTemplate.update("update books set person_id=? where id=?", person.getId(), id);
    }
    //кому принадлежит книга, с указанным id
    public Optional<Person> whoseBook(int id){
        return jdbcTemplate.query("Select person.* from books join person on books.person_id=person.id where books.id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }
}
