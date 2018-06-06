package servlet.servlets;

import java.util.List;
import java.util.Map;

public interface TransferService {
    Map<String, List<CoordinateVo>> transfer(List<String> roomNoPaths);
}
