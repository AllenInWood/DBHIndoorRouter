package servlet.servlets.beacon;

import java.io.*;
import java.net.URL;

public class BeaconInfoReaderImpl implements BeaconInfoReader {

    public String readBeaconFromSourceFile() {
        StringBuilder beaconInfo = new StringBuilder();
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            URL beaconUrl = classLoader.getResource("beacon_placement.json");
            BufferedReader reader = new BufferedReader(
                    new FileReader(new File(beaconUrl.getFile())));
            String temp;
            while ((temp = reader.readLine()) != null) {
                beaconInfo.append(temp);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return beaconInfo.toString();
    }
}