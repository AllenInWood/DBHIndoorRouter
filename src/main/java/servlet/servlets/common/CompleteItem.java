package servlet.servlets.common;

public class CompleteItem {

    private String value;

    private String data;

    public CompleteItem(String value, String data) {
        this.value = value;
        this.data = data;
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
}
