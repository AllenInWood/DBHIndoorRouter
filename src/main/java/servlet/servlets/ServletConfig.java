package servlet.servlets;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;

public class ServletConfig extends GuiceServletContextListener {
    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new ServletModule() {
            @Override
            protected void configureServlets() {
                serve("/route").with(RouteServlet.class);
                serve("/beacon").with(BeaconInfoServlet.class);
                serve("/targets").with(FulltextServlet.class);
            }},
                new MainModule());
    }
}
