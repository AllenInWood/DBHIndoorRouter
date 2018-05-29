package servlet.servlets.coordinates;

public class Coordinate {

    private String xAxis;

    private String yAxis;

    public Coordinate(String xAxis, String yAxis) {
        this.xAxis = xAxis;
        this.yAxis = yAxis;
    }

    public String getxAxis() {
        return xAxis;
    }

    public void setxAxis(String xAxis) {
        this.xAxis = xAxis;
    }

    public String getyAxis() {
        return yAxis;
    }

    public void setyAxis(String yAxis) {
        this.yAxis = yAxis;
    }
}
