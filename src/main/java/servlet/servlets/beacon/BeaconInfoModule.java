package servlet.servlets.beacon;

import com.google.inject.AbstractModule;

public class BeaconInfoModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(BeaconInfoReader.class).to(BeaconInfoReaderImpl.class);
    }
}
