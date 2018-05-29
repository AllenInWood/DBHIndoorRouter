package servlet.servlets.routing;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import servlet.servlets.DBHmap.DBHMapModule;
import servlet.servlets.localize.LocalizeModule;
import servlet.servlets.localize.Localizer;
import javax.inject.Named;
import java.util.List;

public class RouterModule extends AbstractModule{


    @Override
    protected void configure() {
        install(new DBHMapModule());
        install(new LocalizeModule());
        bind(RouterCalculator.class).to(RouterCalculatorImpl.class);
    }

    @Provides @Start
    String getCurrentLocation(Localizer localizer) {
        return localizer.getCurLocationFromTippers().getRoomNo();
    }

    @Provides @Destination
    String getDestinationLocation(Localizer localizer) {
        return localizer.getDestinationFromTippers().getRoomNo();
    }

    @Provides @Named("RoomNoPaths")
    List<String> calculateMinRoute(RouterCalculator routerCalculator) {
        return routerCalculator.getRoutingList();
    }
}
