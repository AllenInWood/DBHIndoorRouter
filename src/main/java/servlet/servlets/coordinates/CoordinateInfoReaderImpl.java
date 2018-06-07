package servlet.servlets.coordinates;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.*;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class CoordinateInfoReaderImpl implements CoordinateInfoReader{

    private String databaseUrl;

    @Inject
    public CoordinateInfoReaderImpl(
            @Named("CoordinateDBName")String databaseUrl) {
        this.databaseUrl = databaseUrl;
    }

    public Map<String, Coordinate> readRoomNoCoordinatesInfoFromSourceFile() {
        Map<String, Coordinate> coordinateMap = new HashMap<String, Coordinate>();
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DriverManager.getConnection(
                    databaseUrl,
                    "root",
                    "root");
            PreparedStatement pstmt = connection
                    .prepareStatement("SELECT * FROM coordinates");
            ResultSet result = pstmt.executeQuery();
            while (result.next()) {
                Coordinate coordinate = new Coordinate(result.getString(3),
                        result.getString(4), result.getString(5));
                coordinateMap.put(result.getString(2), coordinate);
            }
            pstmt.close();
            connection.close();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return coordinateMap;


//        File file = new File(fileName);
//        Map<String, Coordinate> coordinateMap = new HashMap<String, Coordinate>();
//        try {
//            BufferedReader reader = new BufferedReader(new FileReader(file));
//            String line;
//            while ((line = reader.readLine()) != null) {
//                String[] array = line.split(",");
//                Coordinate coordinate = new Coordinate(array[1], array[2], array[3]);
//                coordinateMap.put(array[0], coordinate);
//            }
//            reader.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return coordinateMap;
    }
}
