package com.example.sportradar.entity;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "match")
public class Match implements Serializable {

    @Id
    private String id;

    @NotNull
    private String homeTeam;

    @NotNull
    private String awayTeam;

    private Integer homeScore = 0;

    private Integer awayScore = 0;
}
