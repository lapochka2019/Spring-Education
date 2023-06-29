package ru.lapteva.springcourse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("first")
public class FirstController {
    @GetMapping("/calculator")
    public String calculator(@RequestParam("a") int a, @RequestParam("b") int b,@RequestParam("action")String action, Model model){
        double result=0;
        switch (action){
            case "multiplication": result=a*b; break;
            case "division": result=a/b;break;
            case "subtraction": result=a-b; break;
            case "addition": result=a+b; break;
            default: result=0;break;
        }
        model.addAttribute("res","a = " + a + ", b = " + b + ", action = " + action + "\n The result = " + result);
        return "first/calculator";
    }
    @GetMapping("/hello")
    public String helloPage(@RequestParam(value = "name",required = false) String name,
                            @RequestParam(value = "surname",required = false) String surname,
                            Model model){
        //System.out.println("Hello! " + name + " " + surname);
        model.addAttribute("message","Hello! " + name + " " + surname);
        return "first/hello";
    }

    @GetMapping("bye")
    public String byePage(){
        return "first/bye";
    }
}
