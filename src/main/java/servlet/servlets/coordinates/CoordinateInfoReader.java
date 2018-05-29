package servlet.servlets.coordinates;

import java.util.Map;

public interface CoordinateInfoReader {
    Map<String, Coordinate> readRoomNoCoordinatesInfoFromSourceFile();
}
