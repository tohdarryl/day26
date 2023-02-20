# Day26 Lecture

## MongoDB on MAC terminal
1. Check for services
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

2. Start server
```
brew services start mongodb-community@6.0
```

3. Import data into MongoDB
```
mongoimport "mongodb://localhost:27017" --drop -d shows -c tv --jsonArray --file tv-shows.json
```

--drop if exists
-d database
-c collection/table
--jsonArray type of data 

