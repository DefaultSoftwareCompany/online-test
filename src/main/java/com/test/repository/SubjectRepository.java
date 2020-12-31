package com.test.repository;

import com.test.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Short> {
    public Subject getBySubjectNameIgnoreCase(String subjectName);
}
