package com.test.controller;

import com.test.model.TestWorkers;
import com.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/api/user/add")
    public ModelAndView addPage(ModelAndView modelAndView) {
        modelAndView.setViewName("user/user-add");
        modelAndView.addObject("user", new TestWorkers());
        return modelAndView;
    }

    @PostMapping("/api/user/add")
    public @ResponseBody
    TestWorkers save(@ModelAttribute TestWorkers user) {
        System.out.println(user);
        try {
            return service.save(user);
        } catch (Exception e) {
            System.out.println(e);
        }
        return user;
    }

    @PostMapping("/api/user/edit/{userId}")
    public ResponseEntity<TestWorkers> edit(@PathVariable Integer userId, @RequestParam String userName, @RequestParam String password) {
        return ResponseEntity.ok(service.edit(userId, userName, password));
    }

    @GetMapping("/api/user/get/{userName}")
    public ResponseEntity<Boolean> checkUserName(@PathVariable String userName) {
        return ResponseEntity.ok(service.checkUserName(userName));
    }
}
