package hexlet.code;
import java.util.Map;

import hexlet.code.parsers.ParserJson;
import hexlet.code.parsers.ParserYaml;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;

public class Differ {
    private static String getFileType(String filePath) {
        String[] parts = filePath.split("\\.");
        return parts[parts.length - 1];
    }

    private static Map<Object, Object> readToMap(String path, String fileType) {
        Map<Object, Object> map = new HashMap<>();
        try {
            if (fileType.equals("json")) {
                String jsonString = Files.readString(Paths.get(path));
                map = ParserJson.parseJson(jsonString);
            } else if (fileType.equals("yaml")) {
                String yamlString = Files.readString(Paths.get(path));
                map = ParserYaml.parseYaml(yamlString);
            } else if (fileType.equals("yml")) {
                String yamlString = Files.readString(Paths.get(path));
                map = ParserYaml.parseYaml(yamlString);
            } else {
                System.err.println("Неподдерживаемый формат файла");
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Ошибка при парсинге JSON: " + e.getMessage());
        }
        return map;
    }

    private static LinkedList<Element> genDiffList(String filePath1, String filePath2) {
        var file1type = getFileType(filePath1);
        var file2type = getFileType(filePath2);
        Map<Object, Object> map1 = new HashMap<>();
        Map<Object, Object> map2 = new HashMap<>();
        if (!file1type.equals(file2type)) {
            System.err.println("Файлы должны быть одного типа!");
        } else {
            map1 = readToMap(filePath1, file1type);
            map2 = readToMap(filePath2, file2type);
        }
        return TreeBuilder.buildTree(map1, map2);
    }

    public static String generate(String filePath1, String filePath2, String format) {
        var list = genDiffList(filePath1, filePath2);
        return Formater.formatList(list, format);
    }

    public static String generate(String filePath1, String filePath2) {
        var list = genDiffList(filePath1, filePath2);
        return Formater.formatList(list, "stylish");
    }
}
