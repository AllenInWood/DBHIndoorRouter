package servlet.servlets.localize;

import com.google.inject.AbstractModule;

public class LocalizeModule extends AbstractModule {

    protected void configure() {
        bind(Localizer.class).to(LocalizerImpl.class);
    }
}
