package com.josdem.springboot.validation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public record ValidatorController() {

    @GetMapping
    String index() {
        return "index";
    }

}