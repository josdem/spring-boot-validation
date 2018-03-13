package com.jos.dem.springboot.validator.controller

import static org.springframework.web.bind.annotation.RequestMethod.GET
import static org.springframework.web.bind.annotation.RequestMethod.POST

import javax.validation.Valid

import org.springframework.stereotype.Controller
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.BindingResult

import com.jos.dem.springboot.validator.model.Person
import com.jos.dem.springboot.validator.command.Command
import com.jos.dem.springboot.validator.command.PersonCommand
import com.jos.dem.springboot.validator.repository.PersonRepository

import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Controller
@RequestMapping('persons/**')
class PersonController {

	@Autowired
	PersonRepository personRepository

	Logger log = LoggerFactory.getLogger(this.class)

	@RequestMapping(method=GET)
	ModelAndView getAll(){
		log.info 'Listing all persons'
		ModelAndView modelAndView = new ModelAndView('persons/list')		
		List<Person> persons = personRepository.findAll()
		modelAndView.addObject('persons', persons)
		modelAndView
	}

	@RequestMapping(value='create', method=GET)
	ModelAndView create(){
		log.info 'Creating person'
		ModelAndView modelAndView = new ModelAndView('persons/create')
		Command personCommand = new PersonCommand()
		modelAndView.addObject('personCommand', personCommand)
		modelAndView
	}

	@RequestMapping(method=POST)
	ModelAndView save(@Valid PersonCommand personCommand, BindingResult bindingResult){
		log.info "Registering new Person: ${personCommand.nickname}"
		ModelAndView modelAndView = new ModelAndView('persons/list')
		if(bindingResult.hasErrors()){
			modelAndView.setViewName('persons/create')
			modelAndView.addObject('personCommand', personCommand)
			return modelAndView
		}
		Person person = new Person(nickname:personCommand.nickname, email:personCommand.email, ein:personCommand.ein)
		personRepository.save(person)
		List<Person> persons = personRepository.findAll()
		modelAndView.addObject('persons', persons)
		modelAndView
	}
	
}