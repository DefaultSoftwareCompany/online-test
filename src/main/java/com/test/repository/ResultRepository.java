package com.test.repository;

import com.test.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {
    public Result findByStudentUserIdAndTestTestId(Integer studentId, Integer testId);
}
