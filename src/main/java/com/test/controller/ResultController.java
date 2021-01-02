package com.test.controller;

import com.test.service.ResultService;
import com.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResultController {
    private final ResultService service;
    private final UserService userService;

    @Autowired
    public ResultController(ResultService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @GetMapping("/api/result/save/{testId}/{score}")
    public ModelAndView save(ModelAndView modelAndView, @PathVariable Integer testId, @PathVariable Byte score) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            modelAndView.setViewName("redirect:/api/subject");
        } else {
            service.save(score, testId, userService.getByUserName(authentication.getName()));
            modelAndView.setViewName("redirect:/api/user/get");
        }
        return modelAndView;
    }
}
