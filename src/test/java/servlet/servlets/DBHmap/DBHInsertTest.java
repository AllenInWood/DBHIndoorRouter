package servlet.servlets.DBHmap;

import com.google.inject.Guice;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;
import javax.inject.Named;
import java.sql.*;
import java.util.Map;

public class DBHInsertTest {


    @Inject
    @Named("DBHMap")
    Map<String, Map<String, Double>> floor;

    @Before
    public void setUp() {
        Guice.createInjector(
                new DBHMapModule())
                .injectMembers(this);
    }

    @Test
    public void testGetMap() {
        for (Map.Entry<String, Map<String, Double>> room : floor.entrySet()) {
            for (Map.Entry<String, Double> costs : room.getValue().entrySet()) {
//                System.out.println(room.getKey() + " -> " + costs.getKey() + " : " + costs.getValue());
                int row = 0;
                try {
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cs237?autoReconnect=true&useSSL=false", "root", "root");
                    PreparedStatement pstmt = connection.prepareStatement("INSERT INTO dbhMap (room1, room2, cost) VALUES (?, ?, ?)");
                    pstmt.setString(1, room.getKey());
                    pstmt.setString(2, costs.getKey());
                    pstmt.setDouble(3, costs.getValue());
                    row = pstmt.executeUpdate();
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

                if (row == 0) {
                    throw new UnsupportedOperationException();
                }
                System.out.println("SUCCESS");
            }
        }
    }
}
