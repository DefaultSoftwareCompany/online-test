package com.test.service;

import com.test.model.Option;
import com.test.model.Question;
import com.test.model.Subject;
import com.test.model.Test;
import com.test.repository.SubjectRepository;
import com.test.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class TestService {
    private final TestRepository repository;
    private final SubjectRepository subjectRepository;

    @Autowired
    public TestService(TestRepository repository, SubjectRepository subjectRepository) {
        this.repository = repository;
        this.subjectRepository = subjectRepository;
    }

    public Test getOne(Integer testId) {
        return repository.getOne(testId);
    }

    public Test save(Test test) throws Exception {
        if (test.getNumberOfTests() == null || test.getNumberOfTests() == 0 || test.getTestName() == null || test.getTestName().isEmpty() || test.getTimeRestriction() == null || test.getTimeRestriction() == 0 || test.getNumberOfOptions() == null || test.getNumberOfOptions() == 0) {
            throw new Exception("The form fields are filled in incorrectly");
        } else {
            if (repository.getByTestNameAndSubjectSubjectNameIgnoreCase(test.getTestName(), test.getSubject().getSubjectName()) == null) {
                if (subjectRepository.getBySubjectNameIgnoreCase(test.getSubject().getSubjectName()) == null) {
                    Subject subject = new Subject();
                    subject.setSubjectName(test.getSubject().getSubjectName().toUpperCase());
                    subject = subjectRepository.save(subject);
                    test.setSubject(subject);
                } else {
                    test.setSubject(subjectRepository.getBySubjectNameIgnoreCase(test.getSubject().getSubjectName()));
                }
                return repository.save(test);
            } else throw new Exception("This test name already exists");
        }
    }

    public Test testStart(Integer testId) throws Exception {
        Test test = repository.getOne(testId);
        List<Question> questions = test.getQuestions();
        if (questions != null) {
            Collections.shuffle(questions);
            questions = questions.subList(0, test.getNumberOfTests());
            for (Question question : questions) {
                List<Option> options = question.getOptions();
                Collections.shuffle(options);
                question.setOptions(options);
            }
            test.setQuestions(questions);
            return test;
        } else throw new Exception("There are no questions in this test");
    }

    public void delete(Integer testId) {
        repository.deleteById(testId);
        return;
    }
}
