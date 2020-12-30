package com.test.repository;

import com.test.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    public Users getByUserNameAndPassword(String userName, String password);

    public Users getByUserName(String userName);

    public Users findByUserId(Integer userId);

    public Boolean existsByUserName(String userName);
}
