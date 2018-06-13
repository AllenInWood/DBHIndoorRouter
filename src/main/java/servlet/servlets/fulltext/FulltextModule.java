package servlet.servlets.fulltext;

import com.google.inject.AbstractModule;

public class FulltextModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(FulltextService.class).to(FulltextServiceImpl.class);
    }
}
