package com.test.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer testId;

    private String testName;

    private String startDate;

    private String startTime;

    private Short timeRestriction;

    private String expirationTime;

    private String numberOfTests;

    private Byte numberOfOptions;

    @OneToMany(mappedBy = "test")
    private List<Result> results;

    @ManyToMany
    @JoinTable(
            name = "test_group",
            joinColumns = @JoinColumn(name = "test_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    private List<Groups> groups;

    @OneToMany(mappedBy = "test", cascade = CascadeType.REMOVE)
    private List<Question> questions;
}
