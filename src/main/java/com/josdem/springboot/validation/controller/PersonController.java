package com.josdem.springboot.validation.controller;

import com.josdem.springboot.validation.command.Command;
import com.josdem.springboot.validation.command.PersonCommand;
import com.josdem.springboot.validation.model.Person;
import com.josdem.springboot.validation.repository.PersonRepository;
import com.josdem.springboot.validation.validator.PersonValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/persons")
public record PersonController(PersonRepository personRepository,
                               PersonValidator personValidator) {

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.addValidators(personValidator);
    }

    @GetMapping
    ModelAndView getAll() {
        log.info("Listing all persons");
        ModelAndView modelAndView = new ModelAndView("persons/list");
        List<Person> persons = personRepository.findAll();
        modelAndView.addObject("persons", persons);
        return modelAndView;
    }

    @GetMapping(value = "/create")
    ModelAndView create() {
        log.info("Creating person");
        ModelAndView modelAndView = new ModelAndView("persons/create");
        Command personCommand = new PersonCommand();
        modelAndView.addObject("personCommand", personCommand);
        return modelAndView;
    }

    @PostMapping
    ModelAndView save(@Valid PersonCommand personCommand, BindingResult bindingResult) {
        log.info("Registering new Person: {}", personCommand.getNickname());
        ModelAndView modelAndView = new ModelAndView("persons/list");
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("persons/create");
            modelAndView.addObject("personCommand", personCommand);
            return modelAndView;
        }
        Person person = new Person(null, personCommand.getNickname(), personCommand.getEmail(), personCommand.getEin());
        personRepository.save(person);
        List<Person> persons = personRepository.findAll();
        modelAndView.addObject("persons", persons);
        return modelAndView;
    }

}
