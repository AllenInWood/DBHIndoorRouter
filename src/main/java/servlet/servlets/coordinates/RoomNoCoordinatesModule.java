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
    }

    @Provides
    @Named("CoordinateInfo")
    Map<String, Coordinate> generateRoomNoCoordinatesMapFromSourceFile(
            CoordinateInfoReader coordinateInfoReader) {
        return coordinateInfoReader.readRoomNoCoordinatesInfoFromSourceFile();
    }
}
