package sg.edu.nus.iss.day26workshop.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import static sg.edu.nus.iss.day26workshop.Constants.*;

@Configuration
public class AppConfig {
        // Mongo database name
        // public static final String BGG = "bgg";

        // Inject the mongo connection String
        @Value("${mongo.url}")
        private String mongoUrl;
    
        // Create and initialize MongoTemplate
        @Bean
        public MongoTemplate createMongoTemplate(){
            // Create a MongoClient with the mongo connection String
            MongoClient client = MongoClients.create(mongoUrl);
            return new MongoTemplate(client, BGG);
        }
}
