package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;

public class Parser {
    public static Map<String, Object> parseJson(String json) {
        Map<String, Object> map = new HashMap<>();
        json = json.trim();

        if (json.startsWith("{") && json.endsWith("}")) {
            json = json.substring(1, json.length() - 1);
        }

        String[] pairs = json.split(",");

        for (String pair : pairs) {
            String[] keyValue = pair.split(":");
            String key = keyValue[0].trim().replaceAll("\"", "");
            String value = keyValue[1].trim();

            if (value.startsWith("\"") && value.endsWith("\"")) {
                value = value.substring(1, value.length() - 1);
            }
            map.put(key, value);
        }

        return map;
    }

    public static Map<String, Object> readJsonToMap(String path) {
        Map<String, Object> map = new HashMap<>();
        try {
            String jsonString = Files.readString(Paths.get(path));
            map = parseJson(jsonString);
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }
        return map;
    }

    public static Map<String, Object> parseYaml(String yaml) {
        Map<String, Object> map = new HashMap<>();
        yaml = yaml.trim();
        String[] lines = yaml.split("\n");

        for (String line : lines) {
            line = line.trim();
            if (line.isEmpty() || line.startsWith("#")) {
                continue;
            }

            String[] keyValue = line.split(":");
            String key = keyValue[0].trim();

            String value;
            if (keyValue.length > 1) {
                value = keyValue[1].trim();
                if (value.startsWith("\"") && value.endsWith("\"")) {
                    value = value.substring(1, value.length() - 1);
                }
            } else {
                value = "";
            }

            map.put(key, value);
        }

        return map;
    }

    public static Map<String, Object> readYamlToMap(String path) {
        Map<String, Object> map = new HashMap<>();
        try {
            String yamlString = Files.readString(Paths.get(path));
            map = parseYaml(yamlString);
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }
        return map;
    }
}
