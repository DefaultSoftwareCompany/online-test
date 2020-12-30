package com.test.service;

import com.test.model.Result;
import com.test.model.ResultJsModel;
import com.test.model.Users;
import com.test.repository.ResultRepository;
import com.test.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResultService {
    private final ResultRepository repository;
    private final TestRepository testRepository;

    @Autowired
    public ResultService(ResultRepository repository, TestRepository testRepository) {
        this.repository = repository;
        this.testRepository = testRepository;
    }

    public Result save(ResultJsModel resultJsModel, Users student) {
        Result result = new Result();
        result.setStudent(student);
        result.setTest(testRepository.getOne(resultJsModel.getTestId()));
        result.setDate(resultJsModel.getDate());
        result.setScore(resultJsModel.getScore());
        return repository.save(result);
    }

}
