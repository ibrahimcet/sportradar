package com.example.sportradar.util;

import com.example.sportradar.entity.Match;
import com.example.sportradar.exception.InvalidRequestException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class ValidateMatchDatasTest {

    @InjectMocks
    private ValidateMatchDatas validateMatchDatas;

    @Test
    public void testValidate_NonPatchableAttribute() throws Exception {
        String input = "{\"id\": 1, \"homeTeam\": \"Viking FK\"}";
        assertThrows(Exception.class, () -> validateMatchDatas.validate(input));
    }


    @Test
    void checkForNonPatchableAttributes() {
        Match match = new Match();
        match.setAwayTeam("awayTeam");
        match.setHomeTeam("homeTeam");
        match.setHomeScore(1);
        match.setAwayScore(3);

        assertThrows(InvalidRequestException.class, () -> validateMatchDatas.checkForNonPatchableAttributes(match));
    }
}
