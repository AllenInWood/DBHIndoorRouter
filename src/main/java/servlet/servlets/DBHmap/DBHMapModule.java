package servlet.servlets.DBHmap;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Names;

import javax.inject.Named;
import java.util.Map;

public class DBHMapModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(DBHMapReader.class).to(DBHMapReaderImpl.class);
    }

    @Provides @Named("DBHMap")
    Map<String, Map<String, Double>> generateDBHMapFromSourceFile(
            DBHMapReader dbhMapReader) {
        return dbhMapReader.readDBHMapFromSourceFile();
    }
}
