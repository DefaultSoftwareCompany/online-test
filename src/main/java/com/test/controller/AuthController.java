package com.test.controller;

import com.test.model.Option;
import com.test.repository.OptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AuthController {
    private final OptionRepository repository;

    @Autowired
    public AuthController(OptionRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public ModelAndView homePage(ModelAndView modelAndView) {
        modelAndView.setViewName("home");
        return modelAndView;
    }

    @GetMapping("/option")
    public ResponseEntity<List<Option>> getAll() {
        return ResponseEntity.ok(repository.findAll());
    }
}
