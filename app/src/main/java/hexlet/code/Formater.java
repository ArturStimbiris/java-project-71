package hexlet.code;

import java.util.LinkedList;

public class Formater {
    public static void stylish(LinkedList<Elem> list) {
        System.out.println("{");
        for (var item : list) {
            System.out.println(item);
        }
        System.out.println("}");
    }
}
