package servlet.servlets.coordinates;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Names;
import servlet.servlets.DBHmap.DBHMapModule;

import javax.inject.Named;
import java.util.Map;

public class RoomNoCoordinatesModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(CoordinateInfoReader.class).to(CoordinateInfoReaderImpl.class);
        bind(String.class)
                .annotatedWith(Names.named("CoordinateDBName"))
                .toInstance("jdbc:mysql://localhost:3306/cs237?autoReconnect=true&useSSL=false");
//                .toInstance("/Users/alleninwood/Desktop/DBHIndoorRouter/src/main/webapp/resources/newCoordinates.txt");
    }

    @Provides
    @Named("CoordinateInfo")
    Map<String, Coordinate> generateRoomNoCoordinatesMapFromSourceFile(
            CoordinateInfoReader coordinateInfoReader) {
        return coordinateInfoReader.readRoomNoCoordinatesInfoFromSourceFile();
    }
}
