package com.test.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
public class Groups {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short groupId;

    @Column(unique = true)
    private String groupName;

    @ManyToOne
    @JoinColumn
    private Subject subject;

    @ManyToMany
    @JoinTable(
            name = "group_student",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<TestWorkers> students;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private TestWorkers teacher;

    @ManyToMany(mappedBy = "groups")
    private List<Test> tests;
}
