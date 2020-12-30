package com.test.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private Byte score;

    @ManyToOne
    @JoinColumn
    private Test test;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Users student;

}
