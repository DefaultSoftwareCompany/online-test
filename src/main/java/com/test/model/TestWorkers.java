package com.test.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Data
@Entity
public class TestWorkers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String userName;

    private String password;

    @ManyToMany(mappedBy = "students")
    private List<Groups> studentGroups;

    @OneToMany(mappedBy = "teacher")
    private List<Groups> teacherGroups;

    @OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE)
    private List<Result> results;

    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Roles> roles;

    @Override
    public String toString() {
        return "TestWorkers{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
