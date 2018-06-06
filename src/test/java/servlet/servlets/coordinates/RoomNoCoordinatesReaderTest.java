package servlet.servlets.coordinates;

import com.google.inject.Guice;
import org.junit.Before;
import org.junit.Test;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Map;

public class RoomNoCoordinatesReaderTest {

    @Inject @Named("CoordinateInfo")
    private Map<String, Coordinate> roomNoCoordinateMap;


    @Before public void setUp() {
        Guice.createInjector(
                new RoomNoCoordinatesModule())
                .injectMembers(this);
    }

    @Test
    public void testGetRoomNoCoordinatesMap() {
        for (String roomNo : roomNoCoordinateMap.keySet()) {
            System.out.println(roomNo + " : ("
                    + roomNoCoordinateMap.get(roomNo).getxAxis() + " , "
                    + roomNoCoordinateMap.get(roomNo).getyAxis() + "), Level : "
                    + roomNoCoordinateMap.get(roomNo).getLevel());
        }
    }
}
