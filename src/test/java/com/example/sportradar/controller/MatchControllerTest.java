package com.example.sportradar.controller;

import com.example.sportradar.entity.Match;
import com.example.sportradar.entity.StartMatch;
import com.example.sportradar.service.MatchService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MatchControllerTest {

    @Mock
    private MatchService matchService;

    @InjectMocks
    private MatchController matchController;

    @Test
    public void testStartingMatch() throws Exception {
        StartMatch startMatchBody = new StartMatch();
        startMatchBody.setAwayTeam("awayTeam");
        startMatchBody.setHomeTeam("homeTeam");
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        Match match = new Match();

        when(matchService.startMatch(startMatchBody)).thenReturn(match);
        ResponseEntity<Match> responseEntity = matchController.startingMatch(startMatchBody, request, response);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(match, responseEntity.getBody());
    }

    @Test
    public void testUpdateScore() throws Exception {
        String matchId = "someMatchId";
        String input = "someInput";
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        Match match = new Match();

        when(matchService.updateScore(matchId, input)).thenReturn(match);
        ResponseEntity<Match> responseEntity = matchController.updateScore(matchId, input, response, request);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(match, responseEntity.getBody());
    }

    @Test
    public void testFinishMatch() throws Exception {
        String matchId = "someMatchId";
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        ResponseEntity<Void> responseEntity = matchController.finishMatch(matchId, response, request);
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(matchService).finishMatch(matchId);
    }

    @Test
    public void testScoreboardList() {
        List<Match> scoreboard = new ArrayList<>();
        when(matchService.scoreboard()).thenReturn(scoreboard);

        ResponseEntity<List<Match>> responseEntity = matchController.ScoreboardList();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(scoreboard, responseEntity.getBody());
    }
}
