package hexlet.code;

public class Elem {
    private String incl;
    private String key;
    private Object value;

    public Elem() {

    }

    public Elem(String incl, String key, Object value) {
        this.incl = incl;
        this.key = key;
        this.value = value;
    }

    public String getIncl() {
        return incl;
    }

    public void setIncl(String incl) {
        this.incl = incl;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return incl + " " + key + ": " + value;
    }
}
