package ca.bytube._13_greedy;

import java.util.Arrays;
import java.util.Comparator;

public class LexicographicalSort {
    public static String lexicographicalSort(String[] strings) {
        Arrays.sort(strings, new Comparator<String>() {
            public int compare(String o1, String o2) {
                return (o1+o2).compareTo(o2+o1);
            }
        });

        StringBuilder res = new StringBuilder();
        for (String each : strings) res.append(each);
        return res.toString();
    }
}
