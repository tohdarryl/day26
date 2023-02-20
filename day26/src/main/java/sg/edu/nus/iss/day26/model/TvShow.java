package sg.edu.nus.iss.day26.model;

import org.bson.Document;

import lombok.Data;
import static sg.edu.nus.iss.day26.Constants.*;

@Data
public class TvShow {

    private int id;
    private String name;
    private String url;
    private float rating;
    

    public static TvShow create(Document doc){
        TvShow tvShow = new TvShow();

        tvShow.setId(doc.getInteger("id"));
        tvShow.setName(doc.getString("name"));
        tvShow.setUrl(doc.getString("url"));
        return tvShow;
    }

    public static TvShow createSummary(Document doc){
        TvShow tvShow = new TvShow();

        tvShow.setId(doc.getInteger("id"));
        tvShow.setName(doc.getString("name"));
        //Bson only takes in 'Double' Type
        Document d = (Document) doc.get("rating");

        try {
            if (d.getDouble("average") != null)
                tvShow.setRating(d.getDouble("average").floatValue());
            else
                tvShow.setRating(0.0f);
        } catch (Exception ex) {
            tvShow.setRating(d.getInteger("average").floatValue());

        }

        return tvShow;
    }

    public static TvShow createDetail(Document doc){
        TvShow tvShow = new TvShow();

        tvShow.setId(doc.getInteger("id"));
        tvShow.setName(doc.getString("name"));
        tvShow.setUrl(doc.getString("url"));
        //Bson only takes in 'Double' Type
        Document d = (Document) doc.get("rating");

        try {
            if (d.getDouble("average") != null)
                tvShow.setRating(d.getDouble("average").floatValue());
            else
                tvShow.setRating(0.0f);
        } catch (Exception ex) {
            tvShow.setRating(d.getInteger("average").floatValue());

        }

        return tvShow;
    }
}
