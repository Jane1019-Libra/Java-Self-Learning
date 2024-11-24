package ca.bytube._18_sorting.cmp;

public class BinarySearch {
    public static int indexOf(int v, int[] array) {
        if (array == null || array.length == 0) return -1;
        int begin = 0;
        int end = array.length;
        while (begin < end) {
            int mid = (begin + end) >> 1;
            if (v == array[mid]) return mid;
            else if (v < array[mid]) end = mid;
            else begin = mid + 1;
        }
        return -1;
    }

    public static int search(int v, int[] array) {
        if (array == null || array.length == 0) return -1;
        int begin = 0;
        int end = array.length;
        while (begin < end) {
            int mid = (begin + end) >> 1;
            if (v < array[mid]) end = mid;
            else begin = mid + 1;
        }
        return begin;
    }
}
