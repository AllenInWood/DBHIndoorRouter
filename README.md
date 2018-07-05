# DBHIndoorRouter
Indoor routing from user's current location to some destination.
Find the nearest exit, nearest restroom from user's current location.
Draw path.
...

# Overview
Routing from source to destination:
<img src="/resources/routing_overview.png"  alt="routing_overview">

One layer detail:
<img src="/resources/routing_oneLayer.png"  alt="routing_oneLayer">

We get user's statistical indoor location from beacon interface in [Tippers](http://tippersweb.ics.uci.edu/):
<img src="/resources/Tippers_beacon_data.jpeg"  alt="Tippers_beacon_data">
And obtain the latest one among them.

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
1. show start and destination icon in front-end.
