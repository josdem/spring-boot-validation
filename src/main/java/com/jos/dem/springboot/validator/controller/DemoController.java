package com.jos.dem.springboot.validation.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class DemoController {

	@RequestMapping('/')
  String index(){
    'index'
  }
	
}