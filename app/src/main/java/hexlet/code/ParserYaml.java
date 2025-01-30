package hexlet.code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ParserYaml {
    public static Map<String, Object> parseYaml(String yamlContent) {
        Map<String, Object> result = new LinkedHashMap<>();
        String[] lines = yamlContent.split("\n");
        int index = 0;

        while (index < lines.length) {
            String line = lines[index].trim();

            if (line.isEmpty() || line.startsWith("#")) {
                index++;
                continue;
            }

            if (line.endsWith(":")) {
                String key = line.substring(0, line.length() - 1).trim();
                index = parseValue(lines, index + 1, result, key);
            } else if (line.contains(":")) {
                String[] parts = line.split(":", 2);
                String key = parts[0].trim();
                String value = parts[1].trim();
                result.put(key, parseValue(value));
                index++;
            } else {
                throw new RuntimeException("Неподдерживаемый формат строки: " + line);
            }
        }
        return new HashMap<>(result);
    }

    private static int parseValue(String[] lines, int index, Map<String, Object> result, String key) {
        if (index >= lines.length) {
            result.put(key, null);
            return index;
        }

        String line = lines[index].trim();

        if (line.startsWith("- ")) {
            List<Object> list = new ArrayList<>();
            while (index < lines.length && lines[index].trim().startsWith("- ")) {
                String value = lines[index].trim().substring(2).trim();
                list.add(parseValue(value));
                index++;
            }
            result.put(key, list);
            return index;
        } else if (line.contains(":")) {
            Map<String, Object> nestedMap = new LinkedHashMap<>();
            while (index < lines.length && !lines[index].trim().isEmpty() && !lines[index].trim().startsWith("#")) {
                String nestedLine = lines[index].trim();
                if (nestedLine.contains(":")) {
                    String[] parts = nestedLine.split(":", 2);
                    String nestedKey = parts[0].trim();
                    String nestedValue = parts[1].trim();
                    nestedMap.put(nestedKey, parseValue(nestedValue));
                }
                index++;
            }
            result.put(key, nestedMap);
            return index;
        } else {
            result.put(key, line);
            return index + 1;
        }
    }

    private static Object parseValue(String value) {
        if (value.equals("true") || value.equals("false")) {
            return Boolean.parseBoolean(value);
        } else if (value.matches("\\d+")) {
            return Integer.parseInt(value);
        } else if (value.matches("\\d+\\.\\d+")) {
            return Double.parseDouble(value);
        } else {
            return value;
        }
    }
}
