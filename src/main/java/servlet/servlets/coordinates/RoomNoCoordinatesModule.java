package servlet.servlets.coordinates;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Names;

import javax.inject.Named;
import java.util.Map;

public class RoomNoCoordinatesModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(CoordinateInfoReader.class).to(CoordinateInfoReaderImpl.class);
        bind(String.class)
                .annotatedWith(Names.named("RoomNoCoordinateInfoFileName"))
                .toInstance("/Users/alleninwood/Desktop/DBHIndoorRouter/src/main/webapp/resources/coordinates.txt");
    }

    @Provides
    @Named("CoordinateInfo")
    Map<String, Coordinate> generateRoomNoCoordinatesMapFromSourceFile(
            CoordinateInfoReader coordinateInfoReader) {
        return coordinateInfoReader.readRoomNoCoordinatesInfoFromSourceFile();
    }
}
