package hexlet.code;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Map;

public class TreeBuilder {
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
}
