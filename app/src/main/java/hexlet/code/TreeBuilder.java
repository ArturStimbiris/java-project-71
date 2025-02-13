package hexlet.code;

import java.util.LinkedList;
import java.util.Map;
import java.util.TreeSet;

public class TreeBuilder {
    public static LinkedList<Element> buildTree(Map<Object, Object> map1, Map<Object, Object> map2) {
        var list = new LinkedList<Element>();
        var keys = new TreeSet<Object>(map1.keySet());
        keys.addAll(map2.keySet());

        keys.forEach(key -> {
            Object value1 = map1.get(key);
            Object value2 = map2.get(key);

            if (!map1.containsKey(key)) {
                list.add(new Element("added", key, value2));
            } else if (!map2.containsKey(key)) {
                list.add(new Element("deleted", key, value1));
            } else if (value1 == null && value2 == null) {
                list.add(new Element("unchanged", key, value1));
            } else if (value1 == null || value2 == null) {
                list.add(new Element("changed", key, value1, value2));
            } else if (value1.equals(value2)) {
                list.add(new Element("unchanged", key, value1));
            } else {
                list.add(new Element("changed", key, value1, value2));
            }
        });

        return list;
    }
}
