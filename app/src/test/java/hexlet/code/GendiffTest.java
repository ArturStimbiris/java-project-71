package hexlet.code;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

class GendiffTest {

    public static String readFixture(String path) {
        String fixtureString = "";
        try {
            fixtureString = Files.readString(Paths.get(path));
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }
        return fixtureString;
    }

    @Test
    public void testDiffJson() throws Exception {
        String filepath1 = "src/test/resources/file1.json";
        String filepath2 = "src/test/resources/file2.json";
        String outStr = Differ.generate(filepath1, filepath2);
        String fixFilepath = "src/test/resources/expectedStylish";
        String expectedOutput = readFixture(fixFilepath);
        assertEquals(expectedOutput.trim(), outStr);
    }

    @Test
    public void testDiffYaml() throws Exception {
        String filepath1 = "src/test/resources/file1.yaml";
        String filepath2 = "src/test/resources/file2.yaml";
        String outStr = Differ.generate(filepath1, filepath2);
        String fixFilepath = "src/test/resources/expectedStylish";
        String expectedOutput = readFixture(fixFilepath);
        assertEquals(expectedOutput.trim(), outStr);
    }

    @Test
    public void testJsonStylish() throws Exception {
        String format = "stylish";
        String filepath1 = "src/test/resources/file1.json";
        String filepath2 = "src/test/resources/file2.json";
        String outStr = Differ.generate(filepath1, filepath2, format);
        String fixFilepath = "src/test/resources/expectedStylish";
        String expectedOutput = readFixture(fixFilepath);
        assertEquals(expectedOutput.trim(), outStr);
    }

    @Test
    public void testJsonPlain() throws Exception {
        String format = "plain";
        String filepath1 = "src/test/resources/file1.json";
        String filepath2 = "src/test/resources/file2.json";
        String outStr = Differ.generate(filepath1, filepath2, format);
        String fixFilepath = "src/test/resources/expectedPlain";
        String expectedOutput = readFixture(fixFilepath);
        assertEquals(expectedOutput.trim(), outStr);
    }

    @Test
    public void testJsonJson() throws Exception {
        String format = "json";
        String filepath1 = "src/test/resources/file1.json";
        String filepath2 = "src/test/resources/file2.json";
        String outStr = Differ.generate(filepath1, filepath2, format);
        String fixFilepath = "src/test/resources/expectedJson";
        String expectedOutput = readFixture(fixFilepath);
        assertEquals(expectedOutput.trim(), outStr);
    }

    @Test
    public void testYamlStylish() throws Exception {
        String format = "stylish";
        String filepath1 = "src/test/resources/file1.yaml";
        String filepath2 = "src/test/resources/file2.yaml";
        String outStr = Differ.generate(filepath1, filepath2, format);
        String fixFilepath = "src/test/resources/expectedStylish";
        String expectedOutput = readFixture(fixFilepath);
        assertEquals(expectedOutput.trim(), outStr);
    }

    @Test
    public void testYamlPlain() throws Exception {
        String format = "plain";
        String filepath1 = "src/test/resources/file1.yaml";
        String filepath2 = "src/test/resources/file2.yaml";
        String outStr = Differ.generate(filepath1, filepath2, format);
        String fixFilepath = "src/test/resources/expectedPlain";
        String expectedOutput = readFixture(fixFilepath);
        assertEquals(expectedOutput.trim(), outStr);
    }

    @Test
    public void testYamlJson() throws Exception {
        String format = "json";
        String filepath1 = "src/test/resources/file1.yaml";
        String filepath2 = "src/test/resources/file2.yaml";
        String outStr = Differ.generate(filepath1, filepath2, format);
        String fixFilepath = "src/test/resources/expectedJson";
        String expectedOutput = readFixture(fixFilepath);
        assertEquals(expectedOutput.trim(), outStr);
    }
}
