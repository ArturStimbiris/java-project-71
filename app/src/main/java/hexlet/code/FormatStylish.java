package hexlet.code;

import java.util.LinkedList;

public class FormatStylish {
    public static String stylish(LinkedList<Elem> list) {
        StringBuilder text = new StringBuilder();
        text.append("{\n");
        for (var item : list) {
            text.append(item + "\n");
        }
        text.append("}");
        return text.toString();
    }
}
