package curtool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CollectionsIterator {

    private List list = Collections.synchronizedList(new ArrayList());

    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        List<String> str2 = strings;

        strings.add("1");
        System.out.println(str2.toString());
    }
}
