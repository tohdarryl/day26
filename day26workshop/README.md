## day26workshop

## Steps

### 1. application.properties
```
mongo.url=mongodb://localhost:27017
OR
mongo.url=MONGO_URL
On Terminal
export MONGO_URL=<url> 
```


### 2. set up folders
```
config
controller
model
repository
service
```

### 3. create/set up config file
```
AppConfig.java
@Configuration
```

### 4. create Constants.java file
```
for public static final String ... = ...

import static <path>.* 
to use Strings
```

### 5. create a model file
```
to set attributes according to task requirements
include getters and setters
- JsonObject toJSON method ; if needed
```

### 6. Need Jakarta.Json Dependency to build JsonObject
```
<dependency> 
            <groupId>org.glassfish</groupId>
            <artifactId>jakarta.json</artifactId> 
            <version>2.0.1</version>
	</dependency>
```

### 7. Repository
```
Specify criteria and return mongoTemplate.find(query, Document.class, COLLECTION_GAMES);
List<Document>
```

### 8. Service
```
Create method to use Repository method and stream() to transform and use data retrieved from MongoDB
List<Game>
e.g.
public List<Game> getGames(Integer offset, Integer limit){
    return gamesRepo.getGames(offset, limit).stream()
                    .map(g -> Game.create(g))
                    .toList();
}
- The Game.create(g) method sets the values for Game attributes after receiving from MongoDB
```


