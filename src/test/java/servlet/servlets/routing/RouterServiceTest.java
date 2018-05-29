package servlet.servlets.routing;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.util.Modules;
import org.junit.Before;
import org.junit.Test;
import servlet.servlets.localize.Localizer;
import servlet.servlets.localize.LocalizerImpl;
import servlet.servlets.localize.Location;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

class LocalizerImplMock extends LocalizerImpl {
    @Override
    public Location getCurLocationFromTippers() {
        Location start = new Location("2", "0");
        return start;
    }

    @Override
    public Location getDestinationFromTippers() {
        Location destination = new Location("2", "2056");
        return destination;
    }
}

public class RouterServiceTest {

    @Inject @Named("RoomNoPaths")
    private List<String> paths;

    @Before
    public void setUp() {
        Guice.createInjector(
                Modules.override(new RouterModule())
                        .with(new AbstractModule() {
                            @Override
                            protected void configure() {
                                bind(Localizer.class).to(LocalizerImplMock.class);
                            }
                        })
                )
                .injectMembers(this);
    }

    @Test
    public void testRouting() {
        for (String path : paths) {
            System.out.println(path);
        }
    }
}
