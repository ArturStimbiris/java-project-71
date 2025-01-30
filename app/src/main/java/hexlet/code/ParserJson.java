package hexlet.code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParserJson {
    public static Map<String, Object> parseJson(String json) {
        Map<String, Object> map = new HashMap<>();
        json = json.trim();

        if (json.startsWith("{") && json.endsWith("}")) {
            json = json.substring(1, json.length() - 1);
        }

        String[] pairs = splitJsonPairs(json);

        for (String pair : pairs) {
            String[] keyValue = pair.split(":", 2);
            String key = keyValue[0].trim().replaceAll("\"", "");
            String value = keyValue[1].trim();

            if (value.startsWith("{") && value.endsWith("}")) {
                map.put(key, parseJson(value));
            } else if (value.startsWith("[") && value.endsWith("]")) {
                map.put(key, parseJsonArray(value));
            } else if (value.startsWith("\"") && value.endsWith("\"")) {
                value = value.substring(1, value.length() - 1);
                map.put(key, value);
            } else {
                map.put(key, value);
            }
        }

        return map;
    }

    private static String[] splitJsonPairs(String json) {
        List<String> pairs = new ArrayList<>();
        int braceCount = 0;
        int bracketCount = 0;
        StringBuilder currentPair = new StringBuilder();

        for (int i = 0; i < json.length(); i++) {
            char c = json.charAt(i);
            if (c == '{') {
                braceCount++;
            } else if (c == '}') {
                braceCount--;
            } else if (c == '[') {
                bracketCount++;
            } else if (c == ']') {
                bracketCount--;
            } else if (c == ',' && braceCount == 0 && bracketCount == 0) {
                pairs.add(currentPair.toString().trim());
                currentPair.setLength(0);
                continue;
            }
            currentPair.append(c);
        }

        if (currentPair.length() > 0) {
            pairs.add(currentPair.toString().trim());
        }

        return pairs.toArray(new String[0]);
    }

    private static List<Object> parseJsonArray(String json) {
        List<Object> list = new ArrayList<>();
        json = json.trim();

        if (json.startsWith("[") && json.endsWith("]")) {
            json = json.substring(1, json.length() - 1);
        }

        String[] elements = splitJsonPairs(json);
        for (String element : elements) {
            if (element.startsWith("{") && element.endsWith("}")) {
                list.add(parseJson(element));
            } else if (element.startsWith("\"") && element.endsWith("\"")) {
                list.add(element.substring(1, element.length() - 1));
            } else {
                list.add(element);
            }
        }

        return list;
    }
}
