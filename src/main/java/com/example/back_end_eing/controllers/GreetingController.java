package com.example.back_end_eing.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

    // Hace
    public class GreetingController {

        @GetMapping(value="/")
        //@RequestMapping(value="/",method=RequestMethod.GET)
        public String hello(){
            return "Hello World!!";
        }
    }

