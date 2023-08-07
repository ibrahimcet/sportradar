package com.example.sportradar.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class StartMatch {
    @NotNull
    private String homeTeam;

    @NotNull
    private String awayTeam;

}
