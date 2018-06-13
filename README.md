# DBHIndoorRouter
Basically route the indoor path and later more functions involve.

# Before Running:
Create a database named "cs237", and populate data from 'cs237.sql' in 'src/main/webapp/resources/'

### beacon
return "beacon_placement.json" String service
### common
Common utilization
### DBHmap
Read from "DBH 2 floor.txt" to retrieve the neighbor-cost map (TYPE : Map<String, Map<String, Double>>) for routing.
### localize
RoomNo-description pairs Object
### routing
Calculate the nearest route based on DBH map and start point and destination room No obtained from front-end.
### coordinates
Read from "coordinates.txt" to get roomNo->coordinate map (TYPE : Map<String, Coordinate>) for transferring.

# Test
RoomNoCoordinatesReaderTest.java : test coordinates module
DBHMapReaderTest.java : test DBHmap module
RouterServiceTest.java : test routing module

# TODO:
1. more upper level service like find nearest restroom.
2. show start and destination icon in front-end.
