package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/area")
public class AreaController {

    @GetMapping("/listar")
    public String listArea(){
        return
    }
}
