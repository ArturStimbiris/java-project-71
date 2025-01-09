package hexlet.code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParserYaml {
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
}
