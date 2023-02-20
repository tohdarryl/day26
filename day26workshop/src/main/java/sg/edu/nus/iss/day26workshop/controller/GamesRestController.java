package sg.edu.nus.iss.day26workshop.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import sg.edu.nus.iss.day26workshop.model.Game;
import sg.edu.nus.iss.day26workshop.model.Games;
import sg.edu.nus.iss.day26workshop.service.GamesService;

@RestController
@RequestMapping(path={"","/"}, produces=MediaType.APPLICATION_JSON_VALUE)
public class GamesRestController {

    @Autowired
    GamesService gamesSvc;
    
    @GetMapping("/games")
    public ResponseEntity<String> getGames(@RequestParam(defaultValue = "0")Integer offset,
                                           @RequestParam(defaultValue = "5")Integer limit){
    List<Game> results = gamesSvc.getGames(offset, limit);
    
    Games games = new Games();

    games.setGames(results);
    games.setOffset(offset);
    games.setLimit(limit);
    games.setTotal(results.size());
    games.setTimestamp(LocalDateTime.now());

    if (results.isEmpty()){
        return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .contentType(MediaType.APPLICATION_JSON)
        .body("No Data. Please check your code.");
    }
    return ResponseEntity
    .status(HttpStatus.OK)
    .contentType(MediaType.APPLICATION_JSON)
    .body(games.toJSON().toString());
    }

    @GetMapping("/games/rank")
    public ResponseEntity<String> getGamesByRanking(@RequestParam(defaultValue = "0")Integer offset,
                                           @RequestParam(defaultValue = "5")Integer limit){
    List<Game> results = gamesSvc.getGamesByRanking(offset, limit);
    
    Games games = new Games();

    games.setGames(results);
    games.setOffset(offset);
    games.setLimit(limit);
    games.setTotal(results.size());
    games.setTimestamp(LocalDateTime.now());

    if (results.isEmpty()){
        return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .contentType(MediaType.APPLICATION_JSON)
        .body("No Data. Please check your code.");
    }
    return ResponseEntity
    .status(HttpStatus.OK)
    .contentType(MediaType.APPLICATION_JSON)
    .body(games.toJSON().toString());
    }

    @GetMapping("/game/{gid}")
    public ResponseEntity<String> getGamesById(@PathVariable Integer gid){
    
    List<Game> results = gamesSvc.getGamesById(gid);
    
    Game g = results.get(0);

  

    if (results.isEmpty()){
        return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .contentType(MediaType.APPLICATION_JSON)
        .body("No Data. Please check your code.");
    }
    return ResponseEntity
    .status(HttpStatus.OK)
    .contentType(MediaType.APPLICATION_JSON)
    .body(g.toJSON2().toString());
    }

}
