package hexlet.code;
import java.util.Map;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;

public class Differ {
    public static String getFileType(String filePath) {
        String[] parts = filePath.split("\\.");
        return parts[parts.length - 1];
    }

    public static Map<Object, Object> readJsonToMap(String path) {
        Map<Object, Object> map = new HashMap<>();
        try {
            String jsonString = Files.readString(Paths.get(path));
            map = ParserJson.parseJson(jsonString);
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Ошибка при парсинге JSON: " + e.getMessage());
        }
        return map;
    }

    public static Map<Object, Object> readYamlToMap(String path) {
        Map<Object, Object> map = new HashMap<>();
        try {
            String yamlString = Files.readString(Paths.get(path));
            map = ParserYaml.parseYaml(yamlString);
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Ошибка при парсинге YAML: " + e.getMessage());
        }
        return map;
    }

    public static LinkedList<Element> genDiffList(String filePath1, String filePath2) {
        var file1type = getFileType(filePath1);
        var file2type = getFileType(filePath2);
        Map<Object, Object> map1 = new HashMap<>();
        Map<Object, Object> map2 = new HashMap<>();
        if (!file1type.equals(file2type)) {
            System.err.println("Файлы должны быть одного типа!");
        } else {
            switch (file1type) {
                case "json":
                    map1 = readJsonToMap(filePath1);
                    map2 = readJsonToMap(filePath2);
                    break;
                case "yaml":
                    map1 = readYamlToMap(filePath1);
                    map2 = readYamlToMap(filePath2);
                    break;
                default:
                    break;
            }
        }
        return TreeBuilder.buildTree(map1, map2);
    }

    public static String generate(String filePath1, String filePath2, String format) {
        var list = genDiffList(filePath1, filePath2);
        return Formater.formatList(list, format);
    }

    public static String generate(String filePath1, String filePath2) {
        var list = genDiffList(filePath1, filePath2);
        return Formater.formatList(list);
    }
}
