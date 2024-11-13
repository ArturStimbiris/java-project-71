package hexlet.code;
import java.util.Map;

public class Differ {
    public static void generate(String filePath1, String filePath2) {
        Map<String, Object> map1 = Parser.readJsonToMap(filePath1);
        Map<String, Object> map2 = Parser.readJsonToMap(filePath2);

        if (!map1.isEmpty() && !map2.isEmpty()) {
            System.out.println("Парсинг JSON в Map:");
            System.out.println(map1);
            System.out.println(map2);
        }
    }
}
