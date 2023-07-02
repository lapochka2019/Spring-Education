package ru.lapteva.springcourse.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Person {
    // id пользователя
    private int id;

    // ФИО пользователя: не пустое, от 2 до 100 симфолов
    @NotEmpty(message = "Имя не должно быть пустым!")
    @Size(min = 2, max = 100, message = "Имя должно содержать от 2 до 100 символов!")
    private String name;
    // год пользователя: больше 1900
    @Min(value = 1900, message = "Введите корректный год рождения!")
    private int year;

    public Person() {
    }

    public Person(int id, String name, int year) {
        this.id = id;
        this.name = name;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
