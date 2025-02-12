package hexlet.code;

public class Elem {
    private String incl;
    private Object key;
    private Object value;

    public Elem() {

    }

    public Elem(String incl, Object key, Object value) {
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

    public Object getKey() {
        return key;
    }

    public void setKey(Object key) {
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
