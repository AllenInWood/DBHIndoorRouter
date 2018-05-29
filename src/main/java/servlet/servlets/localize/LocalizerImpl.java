package servlet.servlets.localize;

public class LocalizerImpl implements Localizer {

    public Location getCurLocationFromTippers() {
        Location start = new Location("2", "0");
        return start;
//        throw new UnsupportedOperationException();
    }

    public Location getDestinationFromTippers() {
        Location destination = new Location("2", "2222");
        return destination;
//        throw new UnsupportedOperationException();
    }
}
