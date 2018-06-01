package servlet.servlets.routing;

import com.google.inject.AbstractModule;
import servlet.servlets.DBHmap.DBHMapModule;

public class RouterModule extends AbstractModule{


    @Override
    protected void configure() {
        install(new DBHMapModule());
        bind(RouterCalculator.class).to(RouterCalculatorImpl.class);
    }

}
