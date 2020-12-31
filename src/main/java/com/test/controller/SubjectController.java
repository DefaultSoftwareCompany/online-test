package com.test.controller;

import com.test.model.Subject;
import com.test.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SubjectController {
    private final SubjectService service;

    @Autowired
    public SubjectController(SubjectService service) {
        this.service = service;
    }

    @GetMapping("/api/subject/add")
    public ModelAndView savePage(ModelAndView modelAndView) {
        modelAndView.setViewName("subject/subject-add");
        modelAndView.addObject("error", "");
        modelAndView.addObject("subject", new Subject());
        return modelAndView;
    }

    @PostMapping("/api/subject/add")
    public ModelAndView saveSubject(ModelAndView modelAndView, @ModelAttribute Subject subject) {
        try {
            service.save(subject);
            modelAndView.setViewName("redirect:/api/subject");
        } catch (Exception e) {
            modelAndView.setViewName("subject/subject-add");
            modelAndView.addObject("error", e.getMessage());
            modelAndView.addObject(subject);
        }
        return modelAndView;
    }

    @GetMapping("/api/subject")
    public ModelAndView subjectList(ModelAndView modelAndView) {
        modelAndView.setViewName("subject/subject-list");
        modelAndView.addObject("subjects", service.getAll());
        return modelAndView;
    }

    @GetMapping("/api/subject/get/{subjectId}")
    public ModelAndView getSubject(ModelAndView modelAndView, @PathVariable Short subjectId) {
        modelAndView.setViewName("subject/subject-tests");
        modelAndView.addObject("subject", service.getOne(subjectId));
        return modelAndView;
    }

}
