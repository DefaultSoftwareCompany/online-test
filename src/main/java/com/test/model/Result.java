package com.test.model;

import com.test.repository.UserRepository;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resultId;

    private Short numberOfTrueAnswers;

    private Short numberOfTests;

    @ManyToOne
    @JoinColumn
    private Test test;

    @ManyToOne
    @JoinColumn
    private TestWorkers student;

}
