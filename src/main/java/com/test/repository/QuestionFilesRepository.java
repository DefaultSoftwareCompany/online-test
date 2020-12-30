package com.test.repository;

import com.test.model.QuestionFiles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionFilesRepository extends JpaRepository<QuestionFiles, Integer> {
}
