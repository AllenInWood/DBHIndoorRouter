package servlet.servlets.localize;

public class Location {

    private String floor;

    private String roomNo;

    public Location() {
    }

    public Location(String floor, String roomNo) {
        this.floor = floor;
        this.roomNo = roomNo;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }
}
