#!/bin/bash

# drop tabl if exits
mongoimport "mongodb://localhost:27017" --drop -d shows -c tv --jsonArray --file tv-shows.json