package hexlet.code;

import java.util.LinkedList;

public class Formater {
    public static String formatList(LinkedList<Elem> list, String format) {
        if (!list.isEmpty()) {
            switch (format) {
                case "stylish":
                    return stylish(list);
                case "plain":
                    return plain(list);
                case "json":
                    return json(list);
                default:
                    throw new IllegalArgumentException("Неизвестный формат: " + format);
            }
        }
        return "";
    }

    public static String formatList(LinkedList<Elem> list) {
        if (!list.isEmpty()) {
            return stylish(list);
        }
        return "";
    }

    public static String stylish(LinkedList<Elem> list) {
        StringBuilder text = new StringBuilder();
        text.append("{\n");
        for (var item : list) {
            text.append(item + "\n");
        }
        text.append("}");
        return text.toString();
    }

    public static String plain(LinkedList<Elem> list) {
        LinkedList<Elem> copyList = new LinkedList<>(list);
        StringBuilder text = new StringBuilder();
        for (var item1 : list) {
            var incl1 = item1.getIncl();
            var key1 = item1.getKey();
            Object val1 = item1.getValue();
            Object val2 = null;
            String mod = "skip";
            switch (incl1) {
                case " ":
                    mod = "skip";
                    break;
                case "-":
                    for (var item2 : copyList) {
                        if (key1.equals(item2.getKey()) && item2.getIncl().equals("+")) {
                            mod = "mod";
                            val1 = item1.getValue();
                            val2 = item2.getValue();
                            copyList.remove(item2);
                            break;
                        }
                    }
                    if (!mod.equals("mod")) {
                        mod = "del";
                    }
                    break;
                case "+":
                    boolean upd = false;
                    for (var item2 : copyList) {
                        if (key1.equals(item2.getKey()) && item2.getIncl().equals("-")) {
                            upd = true;
                            break;
                        }
                    }
                    if (!upd) {
                        mod = "add";
                        val1 = item1.getValue();
                    }
                    break;
                default:
                    break;
            }
            switch (mod) {
                case "skip":
                    break;
                case "mod":
                    text.append("Property '"
                        + key1
                        + "' was updated. From "
                        + formatValue(val1)
                        + " to "
                        + formatValue(val2)
                        + "\n");
                    break;
                case "del":
                    text.append("Property '" + key1 + "' was removed\n");
                    break;
                case "add":
                    text.append("Property '" + key1 + "' was added with value: " + formatValue(val1) + "\n");
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

    public static String json(LinkedList<Elem> list) {
        StringBuilder text = new StringBuilder();
        text.append("{\n");
        for (var item : list) {
            String key = item.getKey();
            Object value = item.getValue();
            String incl = item.getIncl();
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
