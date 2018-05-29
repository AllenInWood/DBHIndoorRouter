package servlet.servlets.DBHmap;
import com.google.inject.Guice;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Map;

public class DBHMapReaderTest {

    @Inject @Named("DBHMap")
    Map<String, Map<String, Double>> floor;

    @Before public void setUp() {
        Guice.createInjector(
                new DBHMapModule())
                .injectMembers(this);
    }

    @Test
    public void testGetMap() {
        for (Map.Entry<String, Map<String, Double>> room : floor.entrySet()) {
            for (Map.Entry<String, Double> costs : room.getValue().entrySet()) {
                System.out.println(room.getKey() + " -> " + costs.getKey() + " : " + costs.getValue());
            }
        }
    }
}
