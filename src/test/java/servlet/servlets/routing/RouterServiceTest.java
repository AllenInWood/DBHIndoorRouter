package servlet.servlets.routing;
import com.google.inject.Guice;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

public class RouterServiceTest {

    @Inject @Named("RoomNoPaths")
    private List<String> paths;

    @Before
    public void setUp() {
        Guice.createInjector(new RouterModule())
                .injectMembers(this);
    }

    @Test
    public void testRouting() {
        for (String path : paths) {
            System.out.println(path);
        }
    }
}
