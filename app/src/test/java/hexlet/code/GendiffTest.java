package hexlet.code;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

class GendiffTest {

    @Test
    public void testParceYamlWithNestedStructures() {
        String testYaml = """
                numbers4:
                  - 4
                  - 5
                  - 6
                chars1:
                  - a
                  - b
                  - c
                obj1:
                  nestedKey: value
                  isNested: true
                """;
        Map<String, Object> outMap = ParserYaml.parseYaml(testYaml);
        Map<String, Object> expectedMap = new HashMap<>();
        expectedMap.put("numbers4", Arrays.asList(4, 5, 6));
        expectedMap.put("chars1", Arrays.asList("a", "b", "c"));
        expectedMap.put("obj1", Map.of("nestedKey", "value", "isNested", true));
        assertEquals(expectedMap, outMap);
    }

    @Test
    public void testParceJson() {
        String testJson = """
                {
                    "host": "hexlet.io",
                    "timeout": 50
                }
                """;
        Map<String, Object> outMap = ParserJson.parseJson(testJson);
        Map<String, Object> expectedMap = new HashMap<>();
        expectedMap.put("host", "hexlet.io");
        expectedMap.put("timeout", "50");
        assertEquals(expectedMap, outMap);
    }

    @Test
    public void testParseJsonWithNestedStructures() {
        String testJson = """
                {
                    "numbers4": [4, 5, 6],
                    "chars1": ["a", "b", "c"],
                    "obj1": {
                        "nestedKey": "value",
                        "isNested": true
                    }
                }
            """;
        Map<String, Object> expectedMap = new HashMap<>();
        expectedMap.put("numbers4", Arrays.asList("4", "5", "6"));
        expectedMap.put("chars1", Arrays.asList("a", "b", "c"));
        expectedMap.put("obj1", Map.of("nestedKey", "value", "isNested", "true"));
        Map<String, Object> outMap = ParserJson.parseJson(testJson);
        assertEquals(expectedMap, outMap);
    }

    @Test
    public void testGetFileType() {
        String filepath = "src/test/resources/file1.json";
        String outType = Differ.getFileType(filepath);
        String expectedType = "json";
        assertEquals(expectedType, outType);
    }

    @Test
    public void testDiffJson() throws Exception {
        String format = "stylish";
        String filepath1 = "src/test/resources/file1.json";
        String filepath2 = "src/test/resources/file2.json";
        String outStr = Differ.generate(filepath1, filepath2, format);
        String expectedOutput = """
            {
            - follow: false
              host: hexlet.io
            - proxy: 123.234.53.22
            - timeout: 50
            + timeout: 20
            + verbose: true
            }""";
        assertEquals(expectedOutput.trim(), outStr);
    }

    @Test
    public void testDiffYaml() throws Exception {
        String format = "stylish";
        String filepath1 = "src/test/resources/file1.yaml";
        String filepath2 = "src/test/resources/file2.yaml";
        String outStr = Differ.generate(filepath1, filepath2, format);
        String expectedOutput = """
            {
            - follow: false
              host: hexlet.io
            - proxy: 123.234.53.22
            - timeout: 50
            + timeout: 20
            + verbose: true
            }""";
        assertEquals(expectedOutput.trim(), outStr);
    }

    @Test
    public void testDiffPlain() throws Exception {
        String format = "plain";
        String filepath1 = "src/test/resources/file1.yaml";
        String filepath2 = "src/test/resources/file2.yaml";
        String outStr = Differ.generate(filepath1, filepath2, format);
        String expectedOutput = """
                Property 'follow' was removed
                Property 'proxy' was removed
                Property 'timeout' was updated. From 50 to 20
                Property 'verbose' was added with value: true
                """;
        assertEquals(expectedOutput.trim(), outStr);
    }
}
