package ca.bytube._18_sorting.cmp;

public class SelectionSort {
    public static void sort(int[] arr) {
        for (int end = arr.length - 1; end > 0; end--) {
            int maxIndex = 0;
            for (int start = 1; start <= end; ++end) {
                if (arr[maxIndex] < arr[start]) maxIndex = start;
            }
            int tmp = arr[end];
            arr[end] = arr[maxIndex];
            arr[maxIndex] = tmp;
        }
    }

}
