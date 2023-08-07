package com.example.sportradar.service;

import com.example.sportradar.entity.Match;
import com.example.sportradar.entity.StartMatch;
import com.example.sportradar.exception.InvalidRequestException;
import com.example.sportradar.repository.MatchRepository;
import com.example.sportradar.service.impl.MatchServiceImpl;
import com.example.sportradar.util.JsonMergePatcher;
import com.example.sportradar.util.ValidateMatchDatas;
import com.fasterxml.jackson.databind.ObjectMapper;
import javassist.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MatchServiceImplTest {

    @InjectMocks
    private MatchServiceImpl service;

    @Mock
    private MatchRepository repository;

    @Mock
    private JsonMergePatcher mergePatcher;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private ValidateMatchDatas validateMatchDatas;

    private Match matchMock;

    @BeforeEach
    void setup(){
        matchMock = new Match();
        matchMock.setHomeTeam("Rosenborg BK");
        matchMock.setAwayTeam("Viking FK");
    }

    @Test
    void startMatch(){
        StartMatch startMatch = new StartMatch();
        startMatch.setHomeTeam("Rosenborg BK");
        startMatch.setAwayTeam("Viking FK");
        when(repository.save(any(Match.class))).thenReturn(matchMock);

        Match response = service.startMatch(startMatch);
        assertNotNull(response);
        assertEquals(response.getAwayTeam(), matchMock.getAwayTeam());
        assertEquals(response.getHomeTeam(), matchMock.getHomeTeam());
        assertEquals(response.getAwayScore(), 0);
        assertEquals(response.getHomeScore(), 0);
    }

    @Test
    void updateScore() throws Exception {
        matchMock.setId("id");
        matchMock.setAwayScore(1);
        matchMock.setHomeScore(4);
        when(repository.findById(anyString())).thenReturn(Optional.ofNullable(matchMock));
        when(mergePatcher.mergePatch(anyString(), any())).thenReturn(matchMock);
        when(repository.save(any(Match.class))).thenReturn(matchMock);

        Match response = service.updateScore("id", "{\n" +
                "    \"awayScore\" :1 ,\n" +
                "    \"homeScore\" :4\n" +
                "}");
        assertNotNull(response);
        assertEquals(response.getId(), "id");
        assertEquals(response.getHomeScore(), 4);
        assertEquals(response.getAwayScore(), 1);
    }

    @Test
    void updateScore_ThrowException_WhenNonPatchableFieldsInRequest() throws Exception {
        String input = "{\n" +
                "    \"awayTeam\" : \"1\" ,\n" +
                "    \"homeScore\" : 4\n" +
                "}";

        matchMock.setId("id");
        matchMock.setAwayTeam("1");
        matchMock.setHomeScore(4);
        when(repository.findById(anyString())).thenReturn(Optional.ofNullable(matchMock));
        doThrow(new InvalidRequestException("")).when(validateMatchDatas).validate(input);

        assertThrows(InvalidRequestException.class, () -> service.updateScore("id", input)) ;

    }

    @Test
    void updateScore_ThrowException_WhenCouldNotFoundMatch() {
        when(repository.findById(anyString())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.updateScore("id", "{\n" +
                "    \"awayScore\" :1 ,\n" +
                "    \"homeScore\" : 4\n" +
                "}")) ;
    }

    @Test
    void finishMatch() throws Exception {
        matchMock.setId("id");
        when(repository.findById(anyString())).thenReturn(Optional.ofNullable(matchMock));
        service.finishMatch("id");
        verify(repository, times(1)).delete(any());
    }

    @Test
    void scoreboard() {
        matchMock.setId("id");

        Match matchMockSecond = new Match();
        matchMockSecond.setAwayTeam("Tromsö");
        matchMockSecond.setAwayScore(0);
        matchMockSecond.setAwayTeam("Sarpsborg");
        matchMockSecond.setHomeScore(1);

        when(repository.findAll()).thenReturn(Arrays.asList(matchMock, matchMockSecond));
        List<Match> responseList = service.scoreboard();
        assertNotNull(responseList);
        assertEquals(responseList.size(), 2);
    }
}
