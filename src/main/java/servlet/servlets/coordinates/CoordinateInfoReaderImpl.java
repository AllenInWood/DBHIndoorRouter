package servlet.servlets.coordinates;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class CoordinateInfoReaderImpl implements CoordinateInfoReader{

    private String fileName;

    @Inject
    public CoordinateInfoReaderImpl(
            @Named("RoomNoCoordinateInfoFileName")String fileName) {
        this.fileName = fileName;
    }

    public Map<String, Coordinate> readRoomNoCoordinatesInfoFromSourceFile() {
        File file = new File(fileName);
        Map<String, Coordinate> coordinateMap = new HashMap<String, Coordinate>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] array = line.split(",");
                Coordinate coordinate = new Coordinate(array[1], array[2], array[3]);
                coordinateMap.put(array[0], coordinate);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return coordinateMap;
    }
}
