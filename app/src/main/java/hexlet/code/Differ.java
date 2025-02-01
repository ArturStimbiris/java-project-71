package hexlet.code;
import java.util.Map;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;

public class Differ {
    public static LinkedList<Elem> addSame(Map<String, Object> map1, Map<String, Object> map2) {
        var sameList = new LinkedList<Elem>();
        var map1Es = map1.entrySet();
        for (var map1E : map1Es) {
            if (map2.containsKey(map1E.getKey())) {
                if (map1E.getValue().equals(map2.get(map1E.getKey()))) {
                    sameList.add(new Elem(" ", map1E.getKey(), map1E.getValue()));
                }
            }
        }
        return sameList;
    }

    public static LinkedList<Elem> addNew(Map<String, Object> map1, Map<String, Object> map2) {
        var newList = new LinkedList<Elem>();
        var map2Es = map2.entrySet();
        for (var map2E : map2Es) {
            if (!map1.containsKey(map2E.getKey())) {
                newList.add(new Elem("+", map2E.getKey(), map2E.getValue()));
            } else {
                if (!map2E.getValue().equals(map1.get(map2E.getKey()))) {
                    newList.add(new Elem("+", map2E.getKey(), map2E.getValue()));
                }
            }
        }
        return newList;
    }

    public static LinkedList<Elem> addDel(Map<String, Object> map1, Map<String, Object> map2) {
        var delList = new LinkedList<Elem>();
        var map1Es = map1.entrySet();
        for (var map1E : map1Es) {
            if (!map2.containsKey(map1E.getKey())) {
                delList.add(new Elem("-", map1E.getKey(), map1E.getValue()));
            } else {
                if (!map1E.getValue().equals(map2.get(map1E.getKey()))) {
                    delList.add(new Elem("-", map1E.getKey(), map1E.getValue()));
                }
            }
        }
        return delList;
    }

    public static void printListDefault(LinkedList<Elem> list) {
        System.out.println("{");
        for (var item : list) {
            System.out.println(item);
        }
        System.out.println("}");
    }

    public static String getFileType(String filePath) {
        String[] parts = filePath.split("\\.");
        return parts[parts.length - 1];
    }

    public static Map<String, Object> readJsonToMap(String path) {
        Map<String, Object> map = new HashMap<>();
        try {
            String jsonString = Files.readString(Paths.get(path));
            map = ParserJson.parseJson(jsonString);
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }
        return map;
    }

    public static Map<String, Object> readYamlToMap(String path) {
        Map<String, Object> map = new HashMap<>();
        try {
            String yamlString = Files.readString(Paths.get(path));
            map = ParserYaml.parseYaml(yamlString);
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }
        return map;
    }

    public static String generate(String filePath1, String filePath2, String format) {
        var file1type = getFileType(filePath1);
        var file2type = getFileType(filePath2);
        Map<String, Object> map1 = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();
        String text = "";
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
            var list = new LinkedList<Elem>();
            list.addAll(addSame(map1, map2));
            list.addAll(addNew(map1, map2));
            list.addAll(addDel(map1, map2));
            Collections.sort(list,
                Comparator.comparing(Elem::getKey)
                    .thenComparing(Comparator.comparing(Elem::getIncl).reversed()));
            if (!map1.isEmpty() && !map2.isEmpty()) {
                switch (format) {
                    case "stylish":
                        text = Formater.stylish(list);
                        break;
                    case "plain":
                        text = Formater.plain(list);
                        break;
                    case "json":
                        break;
                    default:
                        throw new IllegalArgumentException("Неизвестный формат: " + format);
                }
            }
        }
        return text;
    }
}
