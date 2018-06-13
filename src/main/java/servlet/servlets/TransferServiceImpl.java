package servlet.servlets;
import servlet.servlets.coordinates.Coordinate;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransferServiceImpl implements TransferService {

    private Map<String, Coordinate> roomNoCoordinateMap;

    @Inject
    public TransferServiceImpl(
            @Named("CoordinateInfo") Map<String, Coordinate> roomNoCoordinateMap) {
        this.roomNoCoordinateMap = roomNoCoordinateMap;
    }

    public Map<String, List<CoordinateVo>> transfer(List<String> roomNoPaths) {
        Map<String, List<CoordinateVo>> coordinateMap = new HashMap<String, List<CoordinateVo>>();
        for (String roomNo : roomNoPaths) {
            if (roomNoCoordinateMap.containsKey(roomNo)) {
                if (!coordinateMap.containsKey(
                        roomNoCoordinateMap.get(roomNo)
                                .getLevel())) {
                    coordinateMap.put(roomNoCoordinateMap.get(roomNo)
                            .getLevel(), new ArrayList<CoordinateVo>());
                    coordinateMap.get(roomNoCoordinateMap.get(roomNo).getLevel())
                            .add(new CoordinateVo(
                                    roomNoCoordinateMap.get(roomNo).getxAxis(),
                                    roomNoCoordinateMap.get(roomNo).getyAxis()
                            ));
                } else {
                    coordinateMap.get(roomNoCoordinateMap.get(roomNo).getLevel())
                            .add(new CoordinateVo(
                                    roomNoCoordinateMap.get(roomNo).getxAxis(),
                                    roomNoCoordinateMap.get(roomNo).getyAxis()
                            ));
                }
            }
        }
//        for (String key : coordinateMap.keySet()) {
//            System.out.println("key : " + key + "=>");
//            for (CoordinateVo coordinateVo : coordinateMap.get(key)) {
//                System.out.print("x : " + coordinateVo.getxAxis() + ", y : " + coordinateVo.getyAxis());
//            }
//        }
        return coordinateMap;
    }
}
