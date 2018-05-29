package servlet.servlets;

import com.google.inject.AbstractModule;
import servlet.servlets.coordinates.RoomNoCoordinatesModule;
import servlet.servlets.routing.RouterModule;

public class MainModule extends AbstractModule {
    protected void configure() {
        install(new RoomNoCoordinatesModule());
        install(new RouterModule());
        bind(RoomNoCoordinatesTransferService.class)
                .to(RoomNoCoordinatesTransferServiceImpl.class);
    }
}
