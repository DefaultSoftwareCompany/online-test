package com.test.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserThymeleafModel {
    private Users user;

    private int averageScore;

    public int getAverageScore() {
        List<Byte> byteList = this.user.getResults().stream().map(result -> result.getScore()).collect(Collectors.toList());
        if (byteList != null && !byteList.isEmpty()) {
            int sum = 0;
            for (Byte aByte : byteList) {
                sum += aByte;
            }
            return sum / this.user.getResults().size();
        } else return 0;
    }

    public UserThymeleafModel(Users user) {
        this.user = user;
    }

}
