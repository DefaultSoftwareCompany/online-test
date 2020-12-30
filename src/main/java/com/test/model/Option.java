package com.test.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
            columnDefinition = "text",
            nullable = false
    )
    private String optionValue;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Question question;

    @Column(
            columnDefinition = "boolean default false"
    )
    private Boolean isTrue;
}
