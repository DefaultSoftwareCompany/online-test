package com.test.repository;

import com.test.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Byte> {
    public Roles getByRoleName(String roleName);
}
