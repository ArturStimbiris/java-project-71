package hexlet.code;

import java.util.LinkedList;

public class Formater {
    public static String formatList(LinkedList<Element> list, String format) {
        if (!list.isEmpty()) {
            switch (format) {
                case "stylish":
                    return FormatStylish.makeText(list);
                case "plain":
                    return FormatPlain.makeText(list);
                case "json":
                    return FormatJson.makeText(list);
                default:
                    throw new IllegalArgumentException("Неизвестный формат: " + format);
            }
        }
        return "";
    }

    public static String formatList(LinkedList<Element> list) {
        if (!list.isEmpty()) {
            return FormatStylish.makeText(list);
        }
        return "";
    }
}
