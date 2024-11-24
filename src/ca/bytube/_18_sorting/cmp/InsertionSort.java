package ca.bytube._18_sorting.cmp;

public class InsertionSort<T extends Comparable<T>> extends Sort<T> {

    protected void sort() {
        for (int begin = 1; begin < array.length; begin++) {
            int cur = begin;
            while (cur > 0 && cmp(cur, cur - 1) < 0) {
                swap(cur, cur-1);
                cur--;
            }
        }
    }

    // 优化别忘看课件
    protected void sort1() {
        for (int begin = 1; begin < array.length; begin++) {
            int cur = begin;
            while (cur > 0 && cmp(cur, cur - 1) < 0) {
                swap(cur, cur-1);
                cur--;
            }
        }
    }
}