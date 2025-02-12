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
    public static LinkedList<Elem> addSame(Map<Object, Object> map1, Map<Object, Object> map2) {
        var sameList = new LinkedList<Elem>();
        var map1Es = map1.entrySet();
        for (var map1E : map1Es) {
            if (map2.containsKey(map1E.getKey())) {
                Object value1 = map1E.getValue();
                Object value2 = map2.get(map1E.getKey());
                if (value1 == null && value2 == null) {
                    sameList.add(new Elem(" ", map1E.getKey(), null));
                } else if (value1 != null && value1.equals(value2)) {
                    sameList.add(new Elem(" ", map1E.getKey(), value1));
                }
            }
        }
        return sameList;
    }

    public static LinkedList<Elem> addNew(Map<Object, Object> map1, Map<Object, Object> map2) {
        var newList = new LinkedList<Elem>();
        var map2Es = map2.entrySet();
        for (var map2E : map2Es) {
            if (!map1.containsKey(map2E.getKey())) {
                newList.add(new Elem("+", map2E.getKey(), map2E.getValue()));
            } else {
                Object value1 = map1.get(map2E.getKey());
                Object value2 = map2E.getValue();
                if (value1 == null && value2 != null || value1 != null && !value1.equals(value2)) {
                    newList.add(new Elem("+", map2E.getKey(), value2));
                }
            }
        }
        return newList;
    }

    public static LinkedList<Elem> addDel(Map<Object, Object> map1, Map<Object, Object> map2) {
        var delList = new LinkedList<Elem>();
        var map1Es = map1.entrySet();
        for (var map1E : map1Es) {
            if (!map2.containsKey(map1E.getKey())) {
                delList.add(new Elem("-", map1E.getKey(), map1E.getValue()));
            } else {
                Object value1 = map1E.getValue();
                Object value2 = map2.get(map1E.getKey());
                if (value1 == null && value2 != null || value1 != null && !value1.equals(value2)) {
                    delList.add(new Elem("-", map1E.getKey(), value1));
                }
            }
        }
        return delList;
    }

    public static LinkedList<Elem> compareSort(Map<Object, Object> map1, Map<Object, Object> map2) {
        var list = new LinkedList<Elem>();
        list.addAll(addSame(map1, map2));
        list.addAll(addNew(map1, map2));
        list.addAll(addDel(map1, map2));
        Comparator<Elem> comparator = Comparator
            .comparing((Elem elem) -> elem.getKey().toString())
            .thenComparing(Elem::getIncl, Comparator.reverseOrder());
        Collections.sort(list, comparator);
        return list;
    }

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

    public static LinkedList<Elem> genDiffList(String filePath1, String filePath2) {
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
        return compareSort(map1, map2);
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
