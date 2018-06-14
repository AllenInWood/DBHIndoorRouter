package servlet.servlets;

import com.google.inject.AbstractModule;
import servlet.servlets.beacon.BeaconInfoModule;
import servlet.servlets.coordinates.RoomNoCoordinatesModule;
import servlet.servlets.routing.RouterModule;

public class MainModule extends AbstractModule {
    protected void configure() {
        install(new RoomNoCoordinatesModule());
        install(new RouterModule());
        install(new BeaconInfoModule());
        bind(TransferService.class)
                .to(TransferServiceImpl.class);
        bind(FulltextService.class)
                .to(FulltextServiceImpl.class);
    }
}
