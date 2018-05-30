package servlet.servlets.localize;

public class LocalizerUtil{

    private static Location start = null;
    private static Location destination = null;

    private LocalizerUtil() {
    }

    public static Location getCurLocation() {
        if (start == null) {
//            throw new UnsupportedOperationException();
            Location temp = new Location("2", "0");
            return temp;
        }
        return start;
    }

    public static void setCurLocation(String startRoomNo) {
        Location temp = new Location("2", startRoomNo);
        LocalizerUtil.start = temp;
    }

    public static Location getDestination() {
        if (destination == null) {
//            throw new UnsupportedOperationException();
            Location temp = new Location("2", "2222");
            return temp;
        }
        return destination;
    }

    public static void setDestination(String destinationRoomNo) {
        Location temp = new Location("2", destinationRoomNo);
        LocalizerUtil.destination = temp;
    }

}
