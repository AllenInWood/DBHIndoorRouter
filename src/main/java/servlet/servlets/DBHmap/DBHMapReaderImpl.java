package servlet.servlets.DBHmap;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DBHMapReaderImpl implements DBHMapReader{

    private String fileName;

    @Inject
    public DBHMapReaderImpl(@Named("DBHMapInfoFileName") String fileName) {
        this.fileName = fileName;
    }

    public Map<String, Map<String, Double>> readDBHMapFromSourceFile() {
        File file = new File(fileName);
        BufferedReader reader = null;
        Map<String, Map<String, Double>> floorMap = new HashMap<String, Map<String, Double>>();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;

            while ((tempString = reader.readLine()) != null) {
                String[] array = tempString.split(" ");
                if (floorMap.containsKey(array[0])) {
                    floorMap.get(array[0]).put(array[1], Double.valueOf(array[2].toString()));
                }
                else {
                    floorMap.put(array[0], new HashMap<String, Double>());
                    floorMap.get(array[0]).put(array[1], Double.valueOf(array[2].toString()));
                }

                if (floorMap.containsKey(array[1])) {
                    floorMap.get(array[1]).put(array[0], Double.valueOf(array[2].toString()));
                }
                else {
                    floorMap.put(array[1], new HashMap<String, Double>());
                    floorMap.get(array[1]).put(array[0], Double.valueOf(array[2].toString()));
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return floorMap;
    }
}
