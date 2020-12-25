package com.test.service;

import com.test.model.TestWorkers;
import com.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public TestWorkers getByUserNameAndPassword(String userName, String password) {
        return repository.getByUserNameAndPassword(userName, password);
    }

    public TestWorkers getByUserName(String userName) {
        return repository.getByUserName(userName);
    }

    public TestWorkers save(TestWorkers testWorkers) throws Exception {
        if (!testWorkers.getFirstName().trim().isEmpty() && !testWorkers.getLastName().trim().isEmpty() && !testWorkers.getUserName().trim().isEmpty() && !testWorkers.getPassword().trim().isEmpty()) {
            return repository.save(testWorkers);
        } else throw new Exception("Fill out the form completely!");
    }

    public TestWorkers edit(Integer userId, String userName, String password) {
        TestWorkers user = repository.findByUserId(userId);
        user.setUserName(userName);
        user.setPassword(password);
        return repository.save(user);
    }
    public Boolean checkUserName(String userName){
        return repository.existsByUserName(userName);
    }
}
