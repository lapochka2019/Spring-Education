package ru.lapteva.springcourse.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {
    private int id;
    @NotEmpty(message = "Данное поле не может быть пустым!")
    @Size(min = 2, max = 100, message = "Имя автора должно быть от 2 до 100 символов!")
    private String author;
    @NotEmpty (message = "Данное поле не может быть пустым!")
    @Size (min = 2, max = 100, message = "Название книги должно быть от 2 до 100 символов!")
    private String title;
    @Min(value = 1900, message = "Год издания книги не должен быть меньше 1900!")
    private int year;

    public Book() {
    }

    public Book(int id, String author, String title, int year) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
