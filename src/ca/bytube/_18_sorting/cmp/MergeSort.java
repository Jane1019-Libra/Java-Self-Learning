package ca.bytube._18_sorting.cmp;

public class MergeSort<T extends Comparable<T>> extends Sort<T> {
    private T[] help;
    protected void sort() {
        help = (T[]) new Comparable[array.length >> 1];
        sort(0, array.length);
    }

    private void sort(int begin, int end) {
        int mid = (begin + end) >> 1;
        sort(begin, mid);
        sort(mid, end);
        
        merge(begin, mid, end);
    }

    private void merge(int begin, int mid, int end) {
        int li = 0;
        int ri = mid;
        int le = mid - begin;
        int re = end;
        int ai = begin;

        for (int i = li; i < le; ++i) help[i] = array[begin+i];

        while (li<le) {
            if (ri < re && cmp(array[ri], help[li]) < 0) array[ai++] = array[ri++];
            else array[ai++] = help[li++];
        }
    }

}
