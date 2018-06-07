package servlet.servlets.DBHmap;

import javax.inject.Inject;
import javax.inject.Named;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DBHMapReaderImpl implements DBHMapReader{

    private String databaseUrl;

    @Inject
    public DBHMapReaderImpl(
            @Named("DbhDBName") String databaseUrl) {
        this.databaseUrl = databaseUrl;
    }

    public Map<String, Map<String, Double>> readDBHMapFromSourceFile() {
        Map<String, Map<String, Double>> floorMap
                = new HashMap<String, Map<String, Double>>();
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DriverManager.getConnection(
                    databaseUrl,
                    "root",
                    "root");
            PreparedStatement pstmt = connection
                    .prepareStatement("SELECT * FROM dbhMap");
            ResultSet result = pstmt.executeQuery();
            while (result.next()) {
//                System.out.println(result.getString(2)
//                        + " " + result.getString(3)
//                        + " " + result.getDouble(4));
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
