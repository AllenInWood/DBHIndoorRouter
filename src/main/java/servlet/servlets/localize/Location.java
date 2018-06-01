package servlet.servlets.localize;

public class Location {

    private String roomNo;

    private String desc;

    public Location(String roomNo, String desc) {
        this.roomNo = roomNo;
        this.desc = desc;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
