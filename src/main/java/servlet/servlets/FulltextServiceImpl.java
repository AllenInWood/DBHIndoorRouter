package servlet.servlets;
import servlet.servlets.common.CompleteItem;
import servlet.servlets.common.VerifyUtil;
import servlet.servlets.routing.RouterCalculator;

import javax.inject.Inject;
import java.sql.*;
import java.util.*;

public class FulltextServiceImpl implements FulltextService{

    @Inject
    private RouterCalculator routerCalculator;

    public List<CompleteItem> listDestinationsByFullTextIndexing(String currentRoom, String query) {
        List<CompleteItem> resultList = new ArrayList<CompleteItem>();
        if (VerifyUtil.isNumericString(query)) {
            try {
                Properties prop = new Properties();
                ClassLoader classLoader = getClass().getClassLoader();
                prop.load(classLoader.getResourceAsStream("dbconfig.properties"));
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                Connection connection = DriverManager.getConnection(
                        prop.getProperty("db.url"),
                        prop.getProperty("db.username"),
                        prop.getProperty("db.password"));
                PreparedStatement pstmt = connection.prepareStatement(
                        "SELECT * FROM rooms WHERE name like \"%" + query + "%\" LIMIT 7");
                System.out.println("SELECT * FROM rooms WHERE name like \"%" + query + "%\" LIMIT 7");
                ResultSet result = pstmt.executeQuery();
                int index = 1;
                while (result.next()) {
                    String destinationRoomName = result.getString(1);
                    String destinationRoomNo = getDestinationID(destinationRoomName);
                    int cost = routerCalculator.getRoutingList(currentRoom, destinationRoomNo).size();
                    CompleteItem completeItem = new CompleteItem(destinationRoomName, "" + index, cost);
                    resultList.add(completeItem);
                    index++;
                }
                pstmt.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Collections.sort(resultList, new Comparator<CompleteItem>() {
                public int compare(CompleteItem o1, CompleteItem o2) {
                    return o1.getCost() - o2.getCost();
                }
            });
        } else {
            Map<Integer, String> resultMap = new HashMap<Integer, String>();
            String[] queryArray = query.split(" ");
            List<String> queryStrings = new ArrayList<String>();
            for (int i = 0; i < queryArray.length; i++) {
                queryStrings.add((i == 0) ? "+" + queryArray[i].trim() + "*" : "" + queryArray[i].trim() + "*");
            }
            StringBuilder queryString = new StringBuilder();
            for (int i = 0; i < queryStrings.size(); i++) {
                if (i != 0) {
                    queryString.append(" ");
                }
                queryString.append(queryStrings.get(i));
            }

            try {
                Properties prop = new Properties();
                ClassLoader classLoader = getClass().getClassLoader();
                prop.load(classLoader.getResourceAsStream("dbconfig.properties"));
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                Connection connection = DriverManager.getConnection(
                        prop.getProperty("db.url"),
                        prop.getProperty("db.username"),
                        prop.getProperty("db.password"));
                PreparedStatement pstmt = connection.prepareStatement(
                        "SELECT * FROM rooms WHERE MATCH (name) AGAINST (\"" + queryString.toString() + "\" IN BOOLEAN MODE) LIMIT 7");
                System.out.println("sql : " + "SELECT * FROM rooms WHERE MATCH (name) AGAINST (\"" + queryString.toString() + "\" IN BOOLEAN MODE) LIMIT 7");
                ResultSet result = pstmt.executeQuery();

                int index = 1;
                while (result.next()) {
//                System.out.println(currentRoom + " " + query);
                    String destinationRoomName = result.getString(1);
                    String destinationRoomNo = getDestinationID(destinationRoomName);
//                System.out.println(destinationRoomName + " : " + destinationRoomNo);
                    int cost = routerCalculator.getRoutingList(currentRoom, destinationRoomNo).size();
                    CompleteItem completeItem = new CompleteItem(destinationRoomName, "" + index, cost);
                    resultList.add(completeItem);
                    index++;
                }
                pstmt.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Collections.sort(resultList, new Comparator<CompleteItem>() {
                public int compare(CompleteItem o1, CompleteItem o2) {
                    return o1.getCost() - o2.getCost();
                }
            });
        }
        return resultList;
    }

    public String getDestinationID(String destination) {
        Integer res = -1;
        if (destination == null || destination.length() == 0) {
            return res.toString();
        }
        try {
            Properties prop = new Properties();
            ClassLoader classLoader = getClass().getClassLoader();
            prop.load(classLoader.getResourceAsStream("dbconfig.properties"));
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection connection = DriverManager.getConnection(
                    prop.getProperty("db.url"),
                    prop.getProperty("db.username"),
                    prop.getProperty("db.password"));
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT id FROM rooms WHERE name = \"" + destination + "\"");
            System.out.println("sql : " + "SELECT id FROM rooms WHERE name = \"" + destination + "\"");
            ResultSet result = pstmt.executeQuery();

            while (result.next()) {
                res = result.getInt(1);
            }
            result.close();
            pstmt.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res.toString();
    }
}