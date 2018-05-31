# DBHIndoorRouter
Basically route the indoor path and later more functions involve.

# Before Running:
Change file path: 
1. DBHIndoorRouter/src/main/java/servlet/servlets/coordinates/RoomNoCoordinatesModule.java : line 17, change file path to absolute path of 'coordinates.txt' in 'DBHIndoorRouter/src/main/webapp/resources/coordinates.txt'.
2. DBHIndoorRouter/src/main/java/servlet/servlets/DBHmap/DBHMapModule.java : line 17, change file path to absolute path of 'DBH 2 floor.txt' in 'DBHIndoorRouter/src/main/webapp/resources/DBH 2 floor.txt'

### DBHmap
Read from "DBH 2 floor.txt" to retrieve the neighbor-cost map (TYPE : Map<String, Map<String, Double>>) for routing.
### localize
Hard coded getCurLocationFromTippers() and getDestinationFromTippers() function, we should implement it further to get current location and destination from Tippers' interfaces.
Now the start point and destination is hard coded in "LocalizerImpl.java".
### routing
Calculate the nearest route based on DBH map and start point and destination room No.
### coordinates
Read from "coordinates.txt" to get roomNo->coordinate map (TYPE : Map<String, Coordinate>) for transferring.

# Test
RoomNoCoordinatesReaderTest.java : test coordinates module
DBHMapReaderTest.java : test DBHmap module
RouterServiceTest.java : test routing module

# TODO:
1. Migrate two file ("DBH 2 floor.txt" and "coordinates.txt") data into database (may try MongoDB this time).
2. increase Room Entity in background img.