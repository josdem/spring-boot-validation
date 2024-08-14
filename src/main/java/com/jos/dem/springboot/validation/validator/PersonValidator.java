package com.jos.dem.springboot.validation.validator;

import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.stereotype.Component;

import com.jos.dem.springboot.validation.command.PersonCommand;

@Component
public class PersonValidator implements Validator {

  private final String REGEX = "[0-9]+";

  @Override
  public boolean supports(Class<?> clazz) {
    return PersonCommand.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    PersonCommand personCommand = (PersonCommand) target;
    validateEin(errors, personCommand);
  }

  private void validateEin(Errors errors, PersonCommand command) {
    if(!command.getEin().matches(REGEX)){
    	errors.rejectValue("ein", "ein.error.format");
    }
  }

}
