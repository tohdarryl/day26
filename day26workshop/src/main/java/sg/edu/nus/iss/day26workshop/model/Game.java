package sg.edu.nus.iss.day26workshop.model;

import java.time.LocalDateTime;

import org.bson.Document;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Game {
    private Integer gid;
    private String name;
    private Integer year;
    private Integer ranking;
    private Integer usersRated;
    private String url;
    private String image;


    public static Game create(Document d) {
        Game g = new Game();
        g.setGid(d.getInteger("gid"));
        g.setName(d.getString("name"));
        g.setYear(d.getInteger("year"));
        g.setRanking(d.getInteger("ranking"));
        g.setUsersRated(d.getInteger("users_rated"));
        g.setUrl(d.getString("url"));
        g.setImage(d.getString("image"));

        return g;
    }

    public JsonObjectBuilder toJSON(){
        return Json.createObjectBuilder()
        .add("gid", this.getGid())
        .add("name", this.getName());
    }

    public JsonObject toJSON2(){
        return Json.createObjectBuilder()
        .add("gid", this.getGid())
        .add("name", this.getName())
        .add("year", this.getYear())
        .add("ranking", this.getRanking())
        .add("users_rated", this.getUsersRated())
        .add("url", this.getUrl())
        .add("image", this.getImage())
        .add("timestamp", LocalDateTime.now().toString())
        .build();
    }
}
