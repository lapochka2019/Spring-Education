package ru.lapteva.springcourse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.lapteva.springcourse.dao.BooksDAO;
import ru.lapteva.springcourse.dao.PersonDAO;
import ru.lapteva.springcourse.models.Book;
import ru.lapteva.springcourse.models.Person;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("books")
public class BooksController {
    private final BooksDAO booksDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BooksController(BooksDAO booksDAO, PersonDAO personDAO) {
        this.booksDAO = booksDAO;
        this.personDAO = personDAO;
    }
    //создать книгу
    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book){
        return "books/new";

    }
    //внести книгу
    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "books/new";
        booksDAO.create(book);
        return "redirect:/books";
    }

    //изменить книгу
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("book",booksDAO.getBook(id));
        return "books/edit";
    }
    //внести изменения
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book")@Valid Book book, BindingResult bindingResult, @PathVariable("id") int id){
        if (bindingResult.hasErrors())
            return "books/edit";
        booksDAO.update(book,id);
        return "redirect:/books";
    }

    //удалить книгу
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        booksDAO.del(id);
        return "redirect:/books";
    }

    //показать все книги
    @GetMapping()
    public String index (Model model){
        model.addAttribute("books", booksDAO.show());
        return "/books/index";
    }

    //показать конкретную книгу
    @GetMapping("/{id}")
    public String getBook(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person){
        model.addAttribute("book", booksDAO.getBook(id));
        //проверяем, занята ли кем-то книга
        Optional<Person> bookOwner = booksDAO.whoseBook(id);
        if(bookOwner.isPresent())
            //если да, то показываем, кем
            model.addAttribute("owner", bookOwner.get());
        //если нет - то предоставляем список всех пользователей для "получения" книги
        else model.addAttribute("people", personDAO.index());
        return "books/show";
    }

    //занять книгу
    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id, @ModelAttribute("person") Person person) {
        booksDAO.bookIsOccupied(id, person);
        return "redirect:/books/" + id;
    }

    //освободить книгу
    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id){
        booksDAO.bookIsFree(id);
        return "redirect:/books/" + id;
    }

}
