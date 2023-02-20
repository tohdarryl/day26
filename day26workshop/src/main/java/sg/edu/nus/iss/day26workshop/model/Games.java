package sg.edu.nus.iss.day26workshop.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Games {
    private List<Game> games = new LinkedList<>();
    private Integer offset;
    private Integer limit;
    private Integer total;
    private LocalDateTime timestamp;


public JsonObject toJSON(){
    //Converting from Java Object: List to JSON object: Array
    JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
    List<JsonObjectBuilder> listOfGames = this.getGames()
    .stream()
    //Using Game.java JsonObjectBuilder toJSON method to build JsonObject for Game
    .map(g -> g.toJSON())
    .toList();
    //Iterate and add into ArrayBuilder
    for (JsonObjectBuilder x : listOfGames)
    arrBuilder.add(x);

    return Json.createObjectBuilder()
    //.add("games", this.GetGames().toString()) ; if String form suffice
    .add("games", arrBuilder)
    .add("offset", this.getOffset())
    .add("limit", this.getLimit())
    .add("total", this.getTotal())
    .add("timestamp", this.getTimestamp().toString())
    .build();
}
}
