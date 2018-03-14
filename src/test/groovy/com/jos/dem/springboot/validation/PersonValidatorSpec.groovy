package com.jos.dem.springboot.validation

import org.springframework.validation.Errors

import com.jos.dem.springboot.validation.command.PersonCommand
import com.jos.dem.springboot.validation.validator.PersonValidator

import spock.lang.Specification

class PersonValidatorSpec extends Specification {

  PersonValidator validator = new PersonValidator()

  void "should detect EIN error format"(){
    given:'An EIN format'
      String ein = '1'
    and:'A target and a Error'
      PersonCommand target = Mock(PersonCommand)
      Errors errors = Mock(Errors)
    when:'We validate EIN'
      target.ein >> ein
      validator.validate(target, errors)
    then:'We expect an error added'
      1 * errors.rejectValue('ein','ein.error.format')
  }
}
