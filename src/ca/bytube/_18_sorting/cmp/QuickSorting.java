package ca.bytube._18_sorting.cmp;

public class QuickSorting<T extends Comparable<T>> extends Sort<T>{

    protected void sort() {
        sort(0, array.length);
    }

    private void sort(int begin, int end) {
        if (end - begin < 2) return;
        int pivot = pivotIndex(begin,end);
        sort(begin, pivot);
        sort(pivot + 1, end);
    }

    private int pivotIndex(int begin, int end) {
        swap(begin, (int)(begin + Math.random() * (end - begin)));
        T pivot = array[begin];
        end--;
        while (begin < end) {
            while (begin < end) {
                if (cmp(pivot, array[end]) < 0) end--;
                else {
                    array[begin++] = array[end];
                    break;
                }
            }
            while (begin < end) {
                if (cmp(pivot, array[begin]) > 0) begin++;
                else {
                    array[end--] = array[begin];
                    break;
                }
            }
        }
        array[begin] = pivot;
        return begin;
    }

    private int pivotIndex2(int l, int r) {
        T pivotVal = array[l];
        int begin = l;
        int less = l - 1;
        int more = r;
        while (l < more) {
            if (cmp(array[l], pivotVal) == 0) l++;
            else if (cmp(array[l], pivotVal) < 0) swap(++less, l++);
            else swap(l, --more);
        }
        return begin;
    }

    private void swap(int i, int j) {
        T tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}
