package ru.lapteva.springcourse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.lapteva.springcourse.dao.PersonDAO;
import ru.lapteva.springcourse.models.Person;
import ru.lapteva.springcourse.util.PersonValidator;

import javax.validation.Valid;


@Controller
@RequestMapping("people")
public class PeopleController {
    private final PersonDAO personDAO;
    private final PersonValidator personValidator;

    @Autowired
    public PeopleController(PersonDAO personDAO, PersonValidator personValidator) {
        this.personDAO = personDAO;
        this.personValidator = personValidator;
    }
    //показать всех пользователей
    @GetMapping()
    public String index (Model model){
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }
    //показать конкретного пользователя
    @GetMapping ("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("person", personDAO.getPerson(id));
        model.addAttribute("books", personDAO.getBooksByPersonId(id));
        return "people/show";
    }
    //создали нового человека
    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }
    //заполнили этого нового человека данными из формы
    @PostMapping()
    public String create (@ModelAttribute("person") @Valid Person person, BindingResult bindingResult){
        //проверяем на валидацию
        personValidator.validate(person,bindingResult);
        //если ошибка
        if (bindingResult.hasErrors())
            //то возвращаемся к созданию нового пользователя
            return "people/new";
        //если все нормально, то сохраняем НП в БД и перекидываем на общую страницу
        personDAO.create(person);
        return "redirect:/people";
    }

    //изменить пользователя
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("person", personDAO.getPerson(id));
        return "/people/edit";
    }
    //внести эти изменения
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult,
                         @PathVariable("id") int id){
        //personValidator.validate(personDAO,bindingResult);
        if (bindingResult.hasErrors())
            return "people/edit";
        personDAO.update(id, person);
        return "redirect:/people";
    }

    //удалить польозователя
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        personDAO.del(id);
        return "redirect:/people";
    }


}
