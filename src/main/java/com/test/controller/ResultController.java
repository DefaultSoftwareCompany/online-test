package com.test.controller;

import com.test.model.Result;
import com.test.model.ResultJsModel;
import com.test.service.ResultService;
import com.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ResultController {
    private final ResultService service;
    private final UserService userService;

    @Autowired
    public ResultController(ResultService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @PostMapping("/api/result/save")
    public ResponseEntity<Result> save(@RequestBody ResultJsModel result) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) return null;
        else return ResponseEntity.ok(service.save(result, userService.getByUserName(authentication.getName())));
    }
}
