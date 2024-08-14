package com.jos.dem.springboot.validation.repository;

import com.jos.dem.springboot.validation.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Person save(Person person);

    List<Person> findAll();

}