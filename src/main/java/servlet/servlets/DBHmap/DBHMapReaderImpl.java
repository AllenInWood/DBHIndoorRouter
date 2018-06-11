package servlet.servlets.DBHmap;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DBHMapReaderImpl implements DBHMapReader{

    public Map<String, Map<String, Double>> readDBHMapFromSourceFile() {
        Map<String, Map<String, Double>> floorMap
                = new HashMap<String, Map<String, Double>>();
        try {
            Properties prop = new Properties();
            ClassLoader classLoader = getClass().getClassLoader();
            prop.load(classLoader.getResourceAsStream("dbconfig.properties"));
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DriverManager.getConnection(
                    prop.getProperty("db.url"),
                    prop.getProperty("db.username"),
                    prop.getProperty("db.password"));
            PreparedStatement pstmt = connection
                    .prepareStatement("SELECT * FROM dbhMap");
            ResultSet result = pstmt.executeQuery();
            while (result.next()) {
                if (!floorMap.containsKey(result.getString(2))) {
                    floorMap.put(result.getString(2), new HashMap<String, Double>());
                }
                floorMap.get(result.getString(2))
                        .put(result.getString(3),
                                result.getDouble(4));

                if (!floorMap.containsKey(result.getString(3))) {
                    floorMap.put(result.getString(3), new HashMap<String, Double>());
                }
                floorMap.get(result.getString(3))
                        .put(result.getString(2),
                                result.getDouble(4));
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        return floorMap;

        // read from file
//        File file = new File(fileName);
//        BufferedReader reader = null;
//        Map<String, Map<String, Double>> floorMap = new HashMap<String, Map<String, Double>>();
//        try {
//            reader = new BufferedReader(new FileReader(file));
//            String tempString = null;
//
//            while ((tempString = reader.readLine()) != null) {
//                String[] array = tempString.split(" ");
//                if (floorMap.containsKey(array[0])) {
//                    floorMap.get(array[0]).put(array[1], Double.valueOf(array[2].toString()));
//                }
//                else {
//                    floorMap.put(array[0], new HashMap<String, Double>());
//                    floorMap.get(array[0]).put(array[1], Double.valueOf(array[2].toString()));
//                }
//
//                if (floorMap.containsKey(array[1])) {
//                    floorMap.get(array[1]).put(array[0], Double.valueOf(array[2].toString()));
//                }
//                else {
//                    floorMap.put(array[1], new HashMap<String, Double>());
//                    floorMap.get(array[1]).put(array[0], Double.valueOf(array[2].toString()));
//                }
//            }
//            reader.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (reader != null) {
//                try {
//                    reader.close();
//                } catch (IOException e1) {
//                }
//            }
//        }
//        return floorMap;
    }
}
