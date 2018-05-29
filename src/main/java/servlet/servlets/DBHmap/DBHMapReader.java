package servlet.servlets.DBHmap;

import java.util.Map;

public interface DBHMapReader {

    Map<String, Map<String, Double>> readDBHMapFromSourceFile();
}
