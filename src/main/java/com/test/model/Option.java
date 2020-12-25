package com.test.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long optionId;

    @Column(
            columnDefinition = "text"
    )
    private String optionValue;

    @ManyToOne
    @JoinColumn
    private Question question;

    private Boolean isTrue;
}
