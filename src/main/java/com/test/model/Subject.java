package com.test.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Byte subjectId;

    @Column(unique = true, nullable = false)
    private String subjectName;

    @OneToMany(mappedBy = "subject")
    @JsonIgnore
    private List<Test> tests;
}
