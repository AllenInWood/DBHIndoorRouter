package servlet.servlets.routing;
import com.google.inject.Guice;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;
import java.util.List;

public class RouterServiceTest {

    @Inject
    private RouterCalculator routerCalculator;

    @Before
    public void setUp() {
        Guice.createInjector(new RouterModule())
                .injectMembers(this);
    }

    @Test
    public void testRouting() {
        List<String> paths = routerCalculator.getRoutingList("0", "3222");
        for (String path : paths) {
            System.out.println(path);
        }
    }
}
