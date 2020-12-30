package com.test.controller;

import com.test.model.Test;
import com.test.service.QuestionService;
import com.test.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {
    private final TestService testService;
    private final QuestionService questionService;

    @Autowired
    public TestController(TestService testService, QuestionService questionService) {
        this.testService = testService;
        this.questionService = questionService;
    }

    @GetMapping("/api/test/add")
    public ModelAndView savePage(ModelAndView modelAndView) {
        modelAndView.setViewName("test/test-add");
        modelAndView.addObject("error", "");
        modelAndView.addObject("test", new Test());
        return modelAndView;
    }

    @PostMapping("/api/test/add")
    public ModelAndView getTestProperties(@ModelAttribute Test test, ModelAndView modelAndView) {
        try {
            test = testService.save(test);
            modelAndView.setViewName("test/question-files");
            modelAndView.addObject("error", "");
        } catch (Exception e) {
            modelAndView.setViewName("test/test-add");
            modelAndView.addObject("error", e.getMessage());
        }
        modelAndView.addObject(test);
        return modelAndView;
    }

    @PostMapping("/api/test/questions")
    public ModelAndView save(ModelAndView modelAndView, @RequestParam MultipartFile file, @RequestParam Integer testId) {
        try {
            questionService.saveQuestions(file, testId);
            modelAndView.setViewName("redirect:/api/subject/get/" + testService.getOne(testId).getTestId());
        } catch (Exception e) {
            modelAndView.addObject("error", e.getMessage());
            modelAndView.addObject("test", testService.getOne(testId));
            modelAndView.setViewName("test/question-files");
        }
        return modelAndView;
    }

    @GetMapping("/api/test/start/{testId}")
    public ModelAndView startTest(@PathVariable Integer testId, ModelAndView modelAndView) {
        modelAndView.setViewName("test/test-start");
        try {
            modelAndView.addObject("test", testService.testStart(testId));
        } catch (Exception e) {
            modelAndView.setViewName("redirect:/api/subject/get/" + testService.getOne(testId).getTestId());
            testService.delete(testId);
        }
        return modelAndView;
    }
}
