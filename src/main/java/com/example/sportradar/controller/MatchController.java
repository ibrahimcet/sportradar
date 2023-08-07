package com.example.sportradar.controller;

import com.example.sportradar.entity.Match;
import com.example.sportradar.entity.StartMatch;
import com.example.sportradar.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MatchController implements MatchApi{

    private static final Logger logger = LoggerFactory.getLogger(MatchController.class);

    private final MatchService service;

    @Override
    public ResponseEntity<Match> startingMatch(StartMatch body, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Match match = service.startMatch(body);
        logger.info("Started a new match with id:{}", match.getId());
        return new ResponseEntity<>(match, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Match> updateScore(String id, String input, HttpServletResponse response, HttpServletRequest request) throws Exception {
        Match match = service.updateScore(id, input);
        logger.info("Updated match with id:{}", match.getId());
        return ResponseEntity.ok().body(match);
    }

    @Override
    public ResponseEntity<Void> finishMatch(String id, HttpServletResponse response, HttpServletRequest request) throws Exception {
        logger.info("Finish match with id:{}", id);
        service.finishMatch(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<List<Match>> ScoreboardList() {
        List<Match> scoreboard = service.scoreboard();
        return new ResponseEntity<>(scoreboard, HttpStatus.OK);
    }
}
