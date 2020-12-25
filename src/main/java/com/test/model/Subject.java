package com.test.model;

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

    @Column(unique = true)
    private String subjectName;

    @OneToMany(mappedBy = "subject")
    private List<Groups> groups;
}
