package servlet.servlets;
import servlet.servlets.coordinates.Coordinate;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RoomNoCoordinatesTransferServiceImpl implements RoomNoCoordinatesTransferService{

    private List<String> roomNoPaths;
    private Map<String, Coordinate> roomNoCoordinateMap;

    @Inject
    public RoomNoCoordinatesTransferServiceImpl(
            @Named("RoomNoPaths") List<String> roomNoPaths,
            @Named("CoordinateInfo") Map<String, Coordinate> roomNoCoordinateMap) {
        this.roomNoPaths = roomNoPaths;
        this.roomNoCoordinateMap = roomNoCoordinateMap;
    }

    public List<Coordinate> transfer() {
        List<Coordinate> coordinateList = new ArrayList<Coordinate>();
        for (String roomNo : roomNoPaths) {
            if (roomNoCoordinateMap.containsKey(roomNo)) {
                Coordinate curCoordinate = new Coordinate(
                        roomNoCoordinateMap.get(roomNo).getxAxis(),
                        roomNoCoordinateMap.get(roomNo).getyAxis());
                coordinateList.add(curCoordinate);
            }
        }
        return coordinateList;
    }
}
