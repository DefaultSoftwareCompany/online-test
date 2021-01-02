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

    @Column(
            unique = true
    )
    private String testName;

    private Short timeRestriction;

    private Short numberOfTests;

    private Byte numberOfOptions;

    @OneToMany(mappedBy = "test")
    private List<Result> results;

    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL)
    private List<Question> questions;

    @ManyToOne
    @JoinColumn
    private Subject subject;
}
