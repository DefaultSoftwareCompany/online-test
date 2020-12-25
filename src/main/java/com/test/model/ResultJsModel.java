package com.test.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class ResultJsModel {
    private Question question;

    private List<Option> options;

    private Option selectedOption;

}
