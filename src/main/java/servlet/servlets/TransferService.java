package servlet.servlets;

import servlet.servlets.coordinates.Coordinate;

import java.util.List;
import java.util.Map;

public interface TransferService {
    List<Coordinate> transfer(List<String> roomNoPaths);
}
