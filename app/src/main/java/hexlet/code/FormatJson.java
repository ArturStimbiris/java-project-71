package hexlet.code;

import java.util.LinkedList;

public class FormatJson {
    public static String json(LinkedList<Elem> list) {
        StringBuilder text = new StringBuilder();
        text.append("{\n");
        for (var item : list) {
            Object key = item.getKey();
            Object value = item.getValue();
            text.append("  \"").append(key).append("\": ");
            text.append("\"").append(value).append("\"");
            if (!item.equals(list.getLast())) {
                text.append(",");
            }
            text.append("\n");
        }
        text.append("}");
        return text.toString();
    }
}
