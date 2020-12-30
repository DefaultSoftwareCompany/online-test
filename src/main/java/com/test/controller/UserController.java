package com.test.controller;

import com.test.model.Users;
import com.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
        modelAndView.addObject("error", "");
        modelAndView.setViewName("user/register");
        modelAndView.addObject("user", new Users());
        return modelAndView;
    }

    @PostMapping("/api/user/add")
    public ModelAndView save(@ModelAttribute Users user, ModelAndView modelAndView) {
        try {
            user = service.save(user);
            modelAndView.setViewName("home");
        } catch (Exception e) {
            modelAndView.setViewName("user/register");
            modelAndView.addObject(user);
            modelAndView.addObject("error", e.getMessage());
        }
        return modelAndView;
    }

    @GetMapping("/api/user/get")
    public ModelAndView getUser(ModelAndView modelAndView) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken)
            modelAndView.setViewName("home");
        else {
            modelAndView.setViewName("user/cabinet");
            modelAndView.addObject("user", service.getByUserName(authentication.getName()));
        }
        return modelAndView;
    }

    @GetMapping("/api/user/all")
    public ModelAndView getAll(ModelAndView modelAndView) {
        modelAndView.setViewName("user/users-list");
        modelAndView.addObject("users", service.getAll());
        return modelAndView;
    }

    @GetMapping("/api/user/delete/{userId}")
    public ModelAndView delete(@PathVariable Integer userId, ModelAndView modelAndView) {
        modelAndView.setViewName("redirect:/api/user/all");
        service.delete(userId);
        return modelAndView;
    }

    @GetMapping("/api/user/add-to-teacher/{userId}")
    public ModelAndView addToTeachers(@PathVariable Integer userId, ModelAndView modelAndView) {
        modelAndView.setViewName("redirect:/api/user/all");
        service.addTeacherRole(userId);
        return modelAndView;
    }

}
