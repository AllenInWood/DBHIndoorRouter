package servlet.servlets.common;

public class CompleteItem {

    private String value;

    private String data;

    private int cost;

    public CompleteItem() {
    }

    public CompleteItem(String value, String data) {
        this.value = value;
        this.data = data;
    }

    public CompleteItem(String value, String data, int cost) {
        this.value = value;
        this.data = data;
        this.cost = cost;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
