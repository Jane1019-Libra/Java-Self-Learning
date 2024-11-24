package ca.bytube._18_sorting._uncmp;

import java.util.LinkedList;
import java.util.List;

public class BucketSort {

    public static void sort(Double[] array) {
        if (array == null || array.length == 0 || array.length == 1) return;

        List<Double>[] buckets = new LinkedList[array.length];

        for (int i = 0; i < array.length; ++i) {
            int bucketIndex =  (int)(array[i] * array.length);
            List<Double> bucket = buckets[bucketIndex];
            if (bucket == null) {
                bucket = new LinkedList<>();
                buckets[bucketIndex] = bucket;
            }
            bucket.add(array[i]);
        }
        int idx = 0;
        for (int i = 0; i < buckets.length; ++i) {
            if (buckets[i] == null) continue;
            buckets[i].sort(null);
            for (Double element: buckets[i]) {
                array[idx++] = element;
            }
        }
    }
}
