package com.test.repository;

import com.test.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<Test, Integer> {
    public Test getByTestNameAndSubjectSubjectNameIgnoreCase(String testName, String subjectName);

}
