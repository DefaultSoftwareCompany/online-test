package com.test.service;

import com.test.model.Roles;
import com.test.model.UserThymeleafModel;
import com.test.model.Users;
import com.test.repository.RolesRepository;
import com.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;
    private final RolesRepository rolesRepository;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder, UserRepository repository, RolesRepository rolesRepository) {
        this.passwordEncoder = passwordEncoder;
        this.repository = repository;
        this.rolesRepository = rolesRepository;
    }

    public Users getByUserName(String userName) {
        return repository.getByUserName(userName);
    }

    public List<UserThymeleafModel> getAll() {
        return repository.findAll().stream().map(users -> new UserThymeleafModel(users)).collect(Collectors.toList());
    }

    public void addTeacherRole(Integer userId) {
        Users user = repository.findByUserId(userId);
        Set<Roles> roles = user.getRoles();
        roles.add(rolesRepository.getByRoleName("TEACHER"));
        user.setRoles(roles);
        repository.save(user);
    }

    public void delete(Integer userId) {
        repository.deleteById(userId);
    }

    public Users save(Users users) throws Exception {
        if (!users.getFirstName().trim().isEmpty() && !users.getLastName().trim().isEmpty() && !users.getUserName().trim().isEmpty() && !users.getPassword().trim().isEmpty()) {
            if (getAll() != null) {
                if (getByUserName(users.getUserName()) == null) {
                    Set<Roles> roles = new HashSet<>();
                    roles.add(rolesRepository.getByRoleName("USER"));
                    users.setRoles(roles);
                    users.setPassword(passwordEncoder.encode(users.getPassword()));
                    return repository.save(users);
                } else throw new Exception("This username already exists.");
            } else {
                List<Roles> roles = new ArrayList<>();
                Roles role = new Roles();
                role.setRoleName("USER");
                roles.add(role);
                role = new Roles();
                role.setRoleName("OWNER");
                roles.add(role);
                role = new Roles();
                role.setRoleName("TEACHER");
                roles.add(role);
                rolesRepository.saveAll(roles);
                users.setRoles((Set<Roles>) rolesRepository.findAll());
                return repository.save(users);
            }
        } else throw new Exception("Fill out the form completely!");
    }

}
