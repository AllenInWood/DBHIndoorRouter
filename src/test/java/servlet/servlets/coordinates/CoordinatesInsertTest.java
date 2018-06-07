package servlet.servlets.coordinates;

import com.google.inject.Guice;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;
import javax.inject.Named;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public class CoordinatesInsertTest {

    @Inject
    @Named("CoordinateInfo")
    private Map<String, Coordinate> roomNoCoordinateMap;


    @Before
    public void setUp() {
        Guice.createInjector(
                new RoomNoCoordinatesModule())
                .injectMembers(this);
    }

    @Test
    public void testGetRoomNoCoordinatesMap() {
        int row = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/cs237?autoReconnect=true&useSSL=false",
                    "root",
                    "root");
            for (String roomNo : roomNoCoordinateMap.keySet()) {
                System.out.println(roomNo + " : ("
                    + roomNoCoordinateMap.get(roomNo).getxAxis() + " , "
                    + roomNoCoordinateMap.get(roomNo).getyAxis() + "), Level : "
                    + roomNoCoordinateMap.get(roomNo).getLevel());

                PreparedStatement pstmt = connection.prepareStatement(
                        "INSERT INTO coordinates (roomNo, xAxis, yAxis, level) VALUES (?, ?, ?, ?)");
                pstmt.setString(1, roomNo);
                pstmt.setString(2, roomNoCoordinateMap.get(roomNo).getxAxis());
                pstmt.setString(3, roomNoCoordinateMap.get(roomNo).getyAxis());
                pstmt.setString(4, roomNoCoordinateMap.get(roomNo).getLevel());
                row = pstmt.executeUpdate();
                pstmt.close();
            }
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

        if (row == 0) {
            throw new UnsupportedOperationException();
        }
    }
}
