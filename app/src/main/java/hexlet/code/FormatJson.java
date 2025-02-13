package hexlet.code;

import java.util.LinkedList;

public class FormatJson {
    public static String makeText(LinkedList<Element> list) {
        StringBuilder text = new StringBuilder();
        text.append("{\n");
        for (var item : list) {
            Object key = item.getKey();
            Object value1 = item.getValue1();
            Object value2 = item.getValue2();
            if (item.getIncl().equals("changed")) {
                text.append("  \"" + key + "\": ");
                text.append("\"" + value1 + "\"");
                text.append(",");
                text.append("\n");
                text.append("  \"" + key + "\": ");
                text.append("\"" + value2 + "\"");
            } else {
                text.append("  \"" + key + "\": ");
                text.append("\"" + value1 + "\"");
            }
            if (!item.equals(list.getLast())) {
                text.append(",");
            }
            text.append("\n");
        }
        text.append("}");
        return text.toString();
    }
}
