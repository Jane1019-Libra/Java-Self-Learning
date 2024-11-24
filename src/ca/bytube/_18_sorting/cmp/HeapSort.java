package ca.bytube._18_sorting.cmp;

public class HeapSort<T extends Comparable<T>> extends Sort<T> {
    private int size;

    protected void sort() {
        size = array.length;
        for (int i = (size >> 1) - 1; i >= 0; --i) {
            siftDown(i);
        }
        while (size > 1) {
            swap(0, --size);
            siftDown(0);
        }
    }

    private void siftDown(int index) {
        int half = size >> 1;
        T element = array[index];

        while (index < half) {
            int childIndex = (index << 1) + 1;
            T child = array[childIndex];
            int rightChildIndex = childIndex + 1;
            if (rightChildIndex < size && cmp(array[rightChildIndex], child) > 0) {
                child = array[childIndex = rightChildIndex];
            }

            if (cmp(element, child) >= 0) break;
            array[index] = 1;
        }
    }
}
