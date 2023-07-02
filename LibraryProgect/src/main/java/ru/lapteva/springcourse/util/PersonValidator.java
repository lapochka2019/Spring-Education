package ru.lapteva.springcourse.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.lapteva.springcourse.dao.PersonDAO;
import ru.lapteva.springcourse.models.Person;

// данный клас необходим для обеспечения валидации
// то есть для проверки уникальности нового пользователя
// по условиям задачи необходимо запретить возможность создания пользователей с одинаковыми ФИО
@Component
public class PersonValidator implements Validator {
    private final PersonDAO personDAO;

    @Autowired
    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }


    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person=(Person) o;
        if (personDAO.isUniq(person.getName()).isPresent()){
            errors.rejectValue("Имя пользователя", "", "Это имя уже использвуется!");
        }
    }
}
