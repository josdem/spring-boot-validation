package com.jos.dem.springboot.validation.controller;

import com.jos.dem.springboot.validation.command.Command;
import com.jos.dem.springboot.validation.command.PersonCommand;
import com.jos.dem.springboot.validation.model.Person;
import com.jos.dem.springboot.validation.repository.PersonRepository;
import com.jos.dem.springboot.validation.validator.PersonValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private PersonValidator personValidator;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.addValidators(personValidator);
    }

    @RequestMapping(method = GET)
    ModelAndView getAll() {
        log.info("Listing all persons");
        ModelAndView modelAndView = new ModelAndView("persons/list");
        List<Person> persons = personRepository.findAll();
        modelAndView.addObject("persons", persons);
        return modelAndView;
    }

    @RequestMapping(value = "create", method = GET)
    ModelAndView create() {
        log.info("Creating person");
        ModelAndView modelAndView = new ModelAndView("persons/create");
        Command personCommand = new PersonCommand();
        modelAndView.addObject("personCommand", personCommand);
        return modelAndView;
    }

    @RequestMapping(method = POST)
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
