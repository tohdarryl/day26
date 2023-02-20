package sg.edu.nus.iss.day26.repository;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class TvShowsRepo {
    public static final String FIELD_RATING_AVERAGE = "rating.average";
    public static final String FIELD_LANGUAGE = "language";
    public static final String FIELD_TYPE = "type";
    public static final String COLLECTION_TV = "tv";
    @Autowired
    private MongoTemplate template;

    /*
    * db.tv.find({ language: "English"})
    */
    public List<Document> findTvShowsByLanguage(String lang){
        // Create a criteria
        // { language "English"}
        // Criteria criteria1 = Criteria.where(FIELD_LANGUAGE).is(lang);
        // Create a criteria - { language: $regex: 'english', $options'i'}
        Criteria criteria1 = Criteria.where(FIELD_LANGUAGE).regex(lang, "i");

        //Create a query
        Query query = Query.query(criteria1);

        //Perform a query
        return template.find(query, Document.class, COLLECTION_TV);
    }

    /*
    * db.tv.distinct('type')
    */
    public List<String> listAllTypes(){
    // String.class is the property of "type"   
        List<String> types = template.findDistinct(new Query(), FIELD_TYPE, "tv", String.class);
        Collections.sort(types);
        

        //Perform a query
        return types;
    }

        /*
db.tv.find({
    type: {
        $regex: 'Animation',
        $options: 'i'
    }
})
.sort({"rating.average": -1})
.projection({_id: 0,id: 1,name: 1,"rating.average": 1})
.limit(20)
    */

    public List<Document> findTvShowsByType(String type){
        return findTvShowsByType(type, 20, 0);
    }

    public List<Document> findTvShowsByType(String type, int limit, int skip){
     
        Criteria criteria = Criteria.where(FIELD_TYPE).regex(type, "i");

        //Create a query and sort
        Query query = Query.query(criteria)
                            .with(Sort.by(Direction.DESC, FIELD_RATING_AVERAGE))
                            .limit(limit)
                            .skip(skip);
        //Projections
        query.fields()
            .exclude(("_id"))
            .include("id","name",FIELD_RATING_AVERAGE);

        //Perform a query
        return template.find(query, Document.class, COLLECTION_TV);
    }

            /*
db.tv.find({
   id: {
        $in: [3]
    }
});
    */

    public List<Document> findTvShowDetail(int id){
     
        Criteria criteria = Criteria.where("id").in(id);

        //Create a query
        Query query = Query.query(criteria);
                            // .with(Sort.by(Direction.DESC, FIELD_RATING_AVERAGE))
                            // .limit(limit)
                            // .skip(skip);
        //Projections
        query.fields()
            .exclude(("_id"))
            .include("id","name",FIELD_RATING_AVERAGE, "url");

        //Perform a query
        return template.find(query, Document.class, COLLECTION_TV);
    }
}
