package hexlet.code;

import java.util.LinkedList;

public class Formater {
    public static String formatList(LinkedList<Elem> list, String format) {
        if (!list.isEmpty()) {
            switch (format) {
                case "stylish":
                    return FormatStylish.stylish(list);
                case "plain":
                    return FormatPlain.plain(list);
                case "json":
                    return FormatJson.json(list);
                default:
                    throw new IllegalArgumentException("Неизвестный формат: " + format);
            }
        }
        return "";
    }

    public static String formatList(LinkedList<Elem> list) {
        if (!list.isEmpty()) {
            return FormatStylish.stylish(list);
        }
        return "";
    }
}
