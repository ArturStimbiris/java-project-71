package hexlet.code;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GendiffTest {
    private ByteArrayOutputStream output = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(output));
    }

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
        String testJson = "{\n"
            + "\"host\": \"hexlet.io\","
            + "\"timeout\": 50,"
            + "}";
        Map<String, Object> outMap = ParserJson.parseJson(testJson);
        Map<String, Object> expectedMap = new HashMap<>();
        expectedMap.put("host", "hexlet.io");
        expectedMap.put("timeout", "50");
        assertEquals(expectedMap, outMap);
    }

    @Test
    public void testParseJsonWithNestedStructures() {
        String testJson = "{\n"
            + "\"numbers4\": [4, 5, 6],\n"
            + "\"chars1\": [\"a\", \"b\", \"c\"],\n"
            + "\"obj1\": {\n"
            + "  \"nestedKey\": \"value\",\n"
            + "  \"isNested\": true\n"
            + "}\n"
            + "}";
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
        String filepath1 = "src/test/resources/file1.json";
        String filepath2 = "src/test/resources/file2.json";
        Differ.generate(filepath1, filepath2);
        String expectedOutput = "{\n"
            + "- follow: false\n"
            + "  host: hexlet.io\n"
            + "- proxy: 123.234.53.22\n"
            + "- timeout: 50\n"
            + "+ timeout: 20\n"
            + "+ verbose: true\n"
            + "}";
        String outStr = output.toString().trim();
        assertEquals(expectedOutput.trim(), outStr);
    }

    @Test
    public void testDiffYaml() throws Exception {
        String filepath1 = "src/test/resources/file1.yaml";
        String filepath2 = "src/test/resources/file2.yaml";
        Differ.generate(filepath1, filepath2);
        String expectedOutput = "{\n"
            + "- follow: false\n"
            + "  host: hexlet.io\n"
            + "- proxy: 123.234.53.22\n"
            + "- timeout: 50\n"
            + "+ timeout: 20\n"
            + "+ verbose: true\n"
            + "}";
        String outStr = output.toString().trim();
        assertEquals(expectedOutput.trim(), outStr);
    }

    @AfterEach
    public void cleanUpStreams() {
        System.setOut(null);
    }
}
