package com.test.service;

import com.test.model.Result;
import com.test.model.Users;
import com.test.repository.ResultRepository;
import com.test.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ResultService {
    private final ResultRepository repository;
    private final TestRepository testRepository;

    @Autowired
    public ResultService(ResultRepository repository, TestRepository testRepository) {
        this.repository = repository;
        this.testRepository = testRepository;
    }

    public Result save(Byte score, Integer testId, Users student) {
        Result result;
        if (repository.findByStudentUserIdAndTestTestId(student.getUserId(), testId) == null) result = new Result();
        else result = repository.findByStudentUserIdAndTestTestId(student.getUserId(), testId);
        result.setStudent(student);
        result.setTest(testRepository.getOne(testId));
        result.setScore(score);
        result.setDate(new SimpleDateFormat("dd/MM/YY").format(new Date()));
        return repository.save(result);
    }

}
