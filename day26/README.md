# Day26 Lecture

## MongoDB on MAC terminal
### 1. Check for services
MacOS
```
brew services list 
```
Check for mongoDB and collection/table
```
mongosh
show databases
use 'database'
show collections
db.'collection/table'.findOne()
```

### 2. Start server
```
brew services start mongodb-community@6.0
```

### 3. Import data into MongoDB
```
mongoimport "mongodb://localhost:27017" --drop -d shows -c tv --jsonArray --file tv-shows.json
```

--drop if exists
-d database
-c collection/table
--jsonArray type of data 

### 4. Jackson.json Dependency
```
<dependency> 
            <groupId>org.glassfish</groupId>
            <artifactId>jakarta.json</artifactId> 
            <version>2.0.1</version>
	</dependency>
```

### 5. MongoDB commands
```
Get all data : db.<collection_name>.find()
Single condition : db.tv.find({ title: 'Dark Crystal'})
Multiple conditions : db.tv.find({ language: 'English', type: 'Scripted'})
Case insensitive search : db.tv.find({ title: { $regex: "crystal", $options: "i"}, year: 1982 })
Look for _id : db.tv.find({ _id: ObjectId('abc123')})
```

### 6. MongoDB operators
Logical
```
$and, $or, $not, $nor
```
Comparison
```
$eq, $neq, $gt, $gte, $lt, $lte, $in, $nin
```
Element query
```
$exists, $type
```

### 7. MongoDB commands with operators
and + gte + gt
```
db.tv.find({
    $and: [
        { "year": { $gte: 1984 } },
        { "rating.average": { $gt: 5 } }
    ]
})
```
in (check if genre has any one of the values in the array)
```
db.tv.find({
    genre: {
        $in: [ "Drama", "Horror", "Adventure" ]
        }
})
```
exists (find the document which has the awards attribute)
```
db.tv.find({
    awards: { $exists: true}
})
```
distinct (returns an array of unique values for that field)
```
db.tv.distinct('rated')
```
count (count the number of documents returned in the query)
```
db.tv.find({
    genre: {
        $in: [ "Drama", "Horror", "Adventure" ]
        }
}).count()
```
skip limit / sort
```
db.tv.find().skip(<int>).limit(<int>).sort(<1> or <-1>)
sort: 1 = ascending, -1 = descending
```