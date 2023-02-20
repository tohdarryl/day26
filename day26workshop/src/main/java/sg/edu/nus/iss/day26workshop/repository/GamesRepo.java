package sg.edu.nus.iss.day26workshop.repository;

import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import static sg.edu.nus.iss.day26workshop.Constants.*;

@Repository
public class GamesRepo {

    @Autowired
    MongoTemplate template;
    
    // db.games.find().skip(0).limit(5);
    public List<Document> getGames(Integer offset, Integer limit){

        Criteria c = new Criteria();

        Query q = Query.query(c)
                        .limit(limit)
                        .skip(offset);

        return template.find(q, Document.class, COLLECTION_GAMES);
    }
    // Sort in ascending order for rank
    // db.games.find().sort({ranking: 1}).skip(0).limit(5);
    public List<Document> getGamesByRanking(Integer offset, Integer limit){

        Criteria c = new Criteria();

        Query q = Query.query(c)
                        .with(Sort.by(Direction.ASC, FIELD_RANKING))
                        .limit(limit)
                        .skip(offset);

        return template.find(q, Document.class, COLLECTION_GAMES);
    }

    // db.games.find({ gid : 2 });
    public List<Document> getGamesById(Integer gid){

        Criteria c = Criteria.where(FIELD_GID).in(gid);

        Query q = Query.query(c);

        return template.find(q, Document.class, COLLECTION_GAMES);
    }
}


    
