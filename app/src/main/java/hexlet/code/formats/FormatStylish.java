package hexlet.code.formats;

import java.util.LinkedList;

import hexlet.code.Element;

public class FormatStylish {
    public static String makeText(LinkedList<Element> list) {
        StringBuilder text = new StringBuilder();
        text.append("{\n");
        for (var item : list) {
            Object key = item.getKey();
            Object value1 = item.getValue1();
            Object value2 = item.getValue2();
            switch (item.getIncl()) {
                case "unchanged":
                    text.append("  " + key + ": " + value1 + "\n");
                    break;
                case "changed":
                    text.append("- " + key + ": " + value1 + "\n");
                    text.append("+ " + key + ": " + value2 + "\n");
                    break;
                case "deleted":
                    text.append("- " + key + ": " + value1 + "\n");
                    break;
                case "added":
                    text.append("+ " + key + ": " + value1 + "\n");
                    break;
                default:
                    break;
            }
        }
        text.append("}");
        return text.toString();
    }
}
