package com.test.service;

import com.test.model.Subject;
import com.test.repository.SubjectRepository;
import com.test.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {
    private final SubjectRepository repository;

    public SubjectService(SubjectRepository repository, UserRepository userRepository) {
        this.repository = repository;
    }

    public Subject getOne(Byte subjectId) {
        return repository.getOne(subjectId);
    }

    public Subject save(Subject subject) throws Exception {
        if (subject.getSubjectName() == null || subject.getSubjectName().isEmpty())
            throw new Exception("The subject name must be entered correctly");
        else {
            if (repository.getBySubjectNameIgnoreCase(subject.getSubjectName()) == null) {
                subject.setSubjectName(subject.getSubjectName().toUpperCase());
                return repository.save(subject);
            } else throw new Exception("This subject already exists");
        }
    }

    public List<Subject> getAll() {
        return repository.findAll();
    }

}
