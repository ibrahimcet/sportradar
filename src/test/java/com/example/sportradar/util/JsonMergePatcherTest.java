package com.example.sportradar.util;

import com.example.sportradar.entity.Match;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class JsonMergePatcherTest {
    @InjectMocks
    private JsonMergePatcher jsonMergePatcher;

    @BeforeEach
    public void setUp() {
        ObjectMapper objectMapper = new ObjectMapper();
        jsonMergePatcher = new JsonMergePatcher(objectMapper);
    }

    @Test
    public void testValidJsonMergePatch() {
        String json = "{\"awayScore\": 3}";
        Match match = new Match();
        match.setAwayScore(0);
        match.setHomeScore(1);

        Match result = jsonMergePatcher.mergePatch(json, match);
        assertEquals(3, result.getAwayScore());
    }

    @Test
    public void testInvalidJsonMergePatch() {
        String json = "{\"invalidField\": 123}";
        Match match = new Match();
        match.setAwayScore(0);
        match.setHomeScore(1);

        assertThrows(IllegalArgumentException.class, () -> jsonMergePatcher.mergePatch(json, match));
    }
}
