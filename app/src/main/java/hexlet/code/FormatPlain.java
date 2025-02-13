package hexlet.code;

import java.util.LinkedList;

public class FormatPlain {
    public static String makeText(LinkedList<Element> list) {
        StringBuilder text = new StringBuilder();
        for (var item : list) {
            Object key = item.getKey();
            Object value1 = item.getValue1();
            Object value2 = item.getValue2();
            switch (item.getIncl()) {
                case "unchanged":
                    break;
                case "changed":
                    text.append("Property '");
                    text.append(key);
                    text.append("' was updated. From ");
                    text.append(formatValue(value1));
                    text.append(" to ");
                    text.append(formatValue(value2));
                    text.append("\n");
                    break;
                case "deleted":
                    text.append("Property '" + key + "' was removed\n");
                    break;
                case "added":
                    text.append("Property '" + key + "' was added with value: " + formatValue(value1) + "\n");
                    break;
                default:
                    break;
            }
        }
        if (text.length() > 0 && text.charAt(text.length() - 1) == '\n') {
            text.deleteCharAt(text.length() - 1);
        }
        return text.toString();
    }

    private static String formatValue(Object val) {
        if (isComplexValue(val)) {
            return "[complex value]";
        }
        if (val instanceof String) {
            return "'" + val + "'";
        }
        return String.valueOf(val);
    }

    private static boolean isComplexValue(Object val) {
        if (val == null) {
            return false;
        }
        if (val.getClass().isArray()) {
            return true;
        }
        return !(val instanceof String || val instanceof Number || val instanceof Boolean);
    }
}
