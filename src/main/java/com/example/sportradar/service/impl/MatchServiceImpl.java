package com.example.sportradar.service.impl;

import com.example.sportradar.entity.Match;
import com.example.sportradar.entity.StartMatch;
import com.example.sportradar.repository.MatchRepository;
import com.example.sportradar.service.MatchService;
import com.example.sportradar.util.JsonMergePatcher;
import com.example.sportradar.util.ValidateMatchDatas;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MatchServiceImpl implements MatchService {

    private final MatchRepository repository;

    private final JsonMergePatcher mergePatcher;

    private final ValidateMatchDatas validateMatchDatas;

    @Override
    public Match startMatch(StartMatch startMatch) {
        Match match = new Match();
        match.setHomeTeam(startMatch.getHomeTeam());
        match.setAwayTeam(startMatch.getAwayTeam());
        return repository.save(match);
    }

    @Override
    public Match updateScore(String id, String input) throws Exception {
        Match existingMatch = getMatch(id);
        validateMatchDatas.validate(input);
        Match patchedMatch = mergePatcher.mergePatch(input, existingMatch);
        repository.save(patchedMatch);
        return patchedMatch;
    }

    @Override
    public void finishMatch(String id) throws Exception {
        Match existingMatch = getMatch(id);
        repository.delete(existingMatch);
    }

    @Override
    public List<Match> scoreboard() {
        List<Match> scoreboard = repository.findAll();
        scoreboard.sort(Comparator.comparingInt(value -> value.getHomeScore() + value.getAwayScore()));
        Collections.reverse(scoreboard);
        return scoreboard;
    }

    private Match getMatch(String id) throws Exception {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Couldn't found Match with id :" + id));
    }
}
