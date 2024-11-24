package ca.bytube._18_sorting.cmp;

public class InsertionSort3<T extends Comparable<T>> extends Sort<T> {

    public static int search(int index) {
        int begin = 0;
        int end = index;
        while (begin < end) {
            int mid = (begin + end) >> 1;
            if (cmp(array[index], array[mid]) < 0) end = mid;
            else begin = mid + 1;
        }
        return begin;
    }

    protected void sort() {
        for (int begin = 1; begin < array.length; begin++) {
            T v = array[begin];
            int insertIndex = search(begin);
            for (int i = begin; i > insertIndex; i--) {
                array[i] = array[i-1];
            }
            array[insertIndex] = v;
        }
    }

}
