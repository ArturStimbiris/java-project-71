package hexlet.code.parsers;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class ParserJson {
    public static Map<Object, Object> parseJson(String content) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(content, Map.class);
    }
}
