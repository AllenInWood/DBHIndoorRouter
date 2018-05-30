package servlet.servlets.routing;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import servlet.servlets.DBHmap.DBHMapModule;
import servlet.servlets.localize.LocalizerUtil;

import javax.inject.Named;
import java.util.List;

public class RouterModule extends AbstractModule{


    @Override
    protected void configure() {
        install(new DBHMapModule());
        bind(RouterCalculator.class).to(RouterCalculatorImpl.class);
    }

    @Provides @Start
    String getCurrentLocation() {
        return LocalizerUtil.getCurLocation().getRoomNo();
    }

    @Provides @Destination
    String getDestinationLocation() {
        return LocalizerUtil.getDestination().getRoomNo();
    }

    @Provides @Named("RoomNoPaths")
    List<String> calculateMinRoute(RouterCalculator routerCalculator) {
        return routerCalculator.getRoutingList();
    }
}
