package servlet.servlets;
import servlet.servlets.coordinates.Coordinate;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TransferServiceImpl implements TransferService {

    private Map<String, Coordinate> roomNoCoordinateMap;

    @Inject
    public TransferServiceImpl(
            @Named("CoordinateInfo") Map<String, Coordinate> roomNoCoordinateMap) {
        this.roomNoCoordinateMap = roomNoCoordinateMap;
    }

    public List<Coordinate> transfer(List<String> roomNoPaths) {
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
