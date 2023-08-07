package com.example.sportradar.service;

import com.example.sportradar.entity.Match;
import com.example.sportradar.entity.StartMatch;

import java.util.List;

public interface MatchService {

    Match startMatch(StartMatch startMatch) throws Exception;

    Match updateScore(String id, String input) throws Exception;

    void finishMatch(String id) throws Exception;

    List<Match> scoreboard();
}
