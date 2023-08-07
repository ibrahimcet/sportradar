package com.example.sportradar.controller;


import com.example.sportradar.entity.Match;
import com.example.sportradar.entity.StartMatch;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.processing.Generated;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen")
@Api(tags = "match",value = "the Match API")
@RequestMapping("/api")
public interface MatchApi {

    @ApiOperation(value = "Start a new match", nickname = "startingMatch", response = Match.class, tags = {"match"})
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created", response = Match.class),
            @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
            @ApiResponse(code = 403, message = "Forbidden", response = Error.class),
            @ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
            @ApiResponse(code = 409, message = "Conflict", response = Error.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class)})
    @RequestMapping(value = "/match",
            produces = {"application/json;charset=utf-8"},
            consumes = {"application/json;charset=utf-8"},
            method = RequestMethod.POST)
    ResponseEntity<Match> startingMatch(@ApiParam(value = "The Match is started", required = true) @RequestBody StartMatch body, HttpServletRequest request, HttpServletResponse response
    ) throws Exception;


    @ApiOperation(value = "Updates Match Score", nickname = "patchMatch", response = Match.class, tags = {"match"})
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Updated", response = Match.class),
                    @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
                    @ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
                    @ApiResponse(code = 403, message = "Forbidden", response = Error.class),
                    @ApiResponse(code = 404, message = "Not Found", response = Error.class),
                    @ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
                    @ApiResponse(code = 409, message = "Conflict", response = Error.class),
                    @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class)
            })
    @RequestMapping(
            value = "/match/{id}",
            produces = {"application/json;charset=utf-8"},
            consumes = {"application/merge-patch+json;charset=utf-8"},
            method = RequestMethod.PATCH)
    ResponseEntity<Match> updateScore(
            @ApiParam(value = "Id of the Match", required = true) @PathVariable("id") String id,
            @ApiParam(value = "The Match to be updated", required = true)
            @RequestBody String input,
            HttpServletResponse response, HttpServletRequest request) throws Exception;

    @ApiOperation(value = "Finish a Match", nickname = "finishMatch", notes = "This operation deletes a Match entity.", tags = {"match"})
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Deleted"),
            @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
            @ApiResponse(code = 403, message = "Forbidden", response = Error.class),
            @ApiResponse(code = 404, message = "Not Found", response = Error.class),
            @ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
            @ApiResponse(code = 409, message = "Conflict", response = Error.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class)})
    @RequestMapping(value = "/match/{id}",
            produces = {"application/json;charset=utf-8"},
            method = RequestMethod.DELETE)
    ResponseEntity<Void> finishMatch(@ApiParam(value = "Identifier of the Catalog", required = true) @PathVariable("id") String id,
                                       HttpServletResponse response, HttpServletRequest request
    ) throws Exception;

    @ApiOperation(value = "Scoreboard List", nickname = "listScoreboard", notes = "This operation list Match entities", response = Match.class, responseContainer = "List", tags = {"match"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = Match.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "Bad Request", response = Error.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = Error.class),
            @ApiResponse(code = 403, message = "Forbidden", response = Error.class),
            @ApiResponse(code = 404, message = "Not Found", response = Error.class),
            @ApiResponse(code = 405, message = "Method Not allowed", response = Error.class),
            @ApiResponse(code = 409, message = "Conflict", response = Error.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Error.class)})
    @RequestMapping(value = "/match",
            produces = {"application/json;charset=utf-8"},
            method = RequestMethod.GET)
    ResponseEntity<List<Match>> ScoreboardList();
}
