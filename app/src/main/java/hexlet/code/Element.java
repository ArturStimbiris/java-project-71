package hexlet.code;

public class Element {
    private String incl;
    private Object key;
    private Object value1;
    private Object value2;

    public Element() {

    }

    public Element(String incl, Object key, Object value1, Object value2) {
        this.incl = incl;
        this.key = key;
        this.value1 = value1;
        this.value2 = value2;
    }

    public Element(String incl, Object key, Object value1) {
        this.incl = incl;
        this.key = key;
        this.value1 = value1;
    }

    public final String getIncl() {
        return incl;
    }

    public final void setIncl(String incl) {
        this.incl = incl;
    }

    public final Object getKey() {
        return key;
    }

    public final void setKey(Object key) {
        this.key = key;
    }

    public final Object getValue1() {
        return value1;
    }

    public final void setValue1(Object value1) {
        this.value1 = value1;
    }

    public final Object getValue2() {
        return value2;
    }

    public final void setValue2(Object value2) {
        this.value2 = value2;
    }

    @Override
    public final String toString() {
        return incl + " " + key + " " + value1 + " " + ((value2 != null) ? value2 : "");
    }
}
