package com.jos.dem.springboot.validation.validator

import org.springframework.validation.Validator
import org.springframework.validation.Errors
import org.springframework.stereotype.Component

import com.jos.dem.springboot.command.PersonCommand

@Component
class PersonValidator implements Validator {

  @Override
  boolean supports(Class<?> clazz) {
    PersonCommand.class.equals(clazz)
  }

  @Override
  void validate(Object target, Errors errors) {
    PersonCommand personCommand = (PersonCommand) target
  }

}