package com.test.model;

import java.util.List;
import java.util.stream.Collectors;

public class UserThymeleafModel {
    private Users user;

    private int averageScore;

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public int getAverageScore() {
        List<Byte> byteList = this.user.getResults().stream().map(result -> result.getScore()).collect(Collectors.toList());
        int sum = 0;
        for (Byte aByte : byteList) {
            sum += aByte;
        }
        return sum / this.user.getResults().size();
    }

    public void setAverageScore(Byte averageScore) {
        this.averageScore = averageScore;
    }

    public UserThymeleafModel() {
    }

    public UserThymeleafModel(Users user) {
        this.user = user;
    }

    public UserThymeleafModel(Users user, Byte averageScore) {
        this.user = user;
        this.averageScore = averageScore;
    }
}
