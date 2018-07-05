# DBHIndoorRouter
Indoor nearest routing in DBH Building, interacting with interfaces applied by Tippers 
Find the nearest exit, nearest restroom from user's current location.
Draw path.
...

# Overview
### Intro
1. Implemented front-end with jQuery, javascript, back-end services dependency injection with Guice in REST standard and database with MySQL for mapping information storage.
2. Through interaction between user's phone and beacons installed in the building, we can obtain data through Tippers.
3. Based on user's input, suggested destinations sorted by distance to user.
4. Displayed routes from user's location to destination (eg. same floor, different floor through elevator etc.).

### Page Display
Routing from source to destination:
<img src="src/main/webapp/resources/routing_overview.png"  alt="routing_overview">

One layer detail:
<img src="src/main/webapp/resources/routing_oneLayer.png"  alt="routing_oneLayer">

In addition, we implemented routing between different layer and its corresponding display.

### Data source
We get user's statistical indoor location from beacon interface in [Tippers](http://tippersweb.ics.uci.edu/):
<img src="src/main/webapp/resources/Tippers_beacon_data.jpg"  alt="Tippers_beacon_data">
And obtain the latest one among them.

# Before Running:
Mark "src/main/webapp/resources" directory as "Resource Root"
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

# Model
First we got the original single floor map of CS Department Hall (Donald Bren Hall):
<img src="src/main/webapp/resources/originMap.jpg"  alt="originMap">

And we divided the path into multiple block and define distance between blocks:
<img src="src/main/webapp/resources/MapModel.jpg"  alt="MapModel">

In the meantime, we draw the map of single floor using third party software:
<img src="src/main/webapp/resources/LayerMap.png"  alt="LayerMap">

# TODO:
1. show start and destination icon in front-end.
