package com.test.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
public class QuestionFiles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer questionsFileId;

    @Column(
            columnDefinition = "text"
    )
    private String filePath;

    private String extension;

    @OneToOne
    @JoinColumn
    private Test test;
}
