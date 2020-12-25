package com.test.repository;

import com.test.model.TestWorkers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<TestWorkers, Integer> {
    public TestWorkers getByUserNameAndPassword(String userName, String password);

    public TestWorkers getByUserName(String userName);

    public TestWorkers findByUserId(Integer userId);

    public Boolean existsByUserName(String userName);
}
