package ca.bytube._18_sorting.cmp;

public class BubbleSort {
    public static void sort(int[] arr) {
        for (int end = arr.length - 1; end > 0; end--) {
            boolean sort = true;
            for (int start = 1; start <= end; ++end) {
                if (arr[start] < arr[start - 1]) {
                    int tmp = arr[start];
                    arr[start] = arr[start - 1];
                    arr[start - 1] = tmp;
                    sort = false;
                }
            }

            if (sort) break;
        }
    }

    public static void sort2(int[] arr) {
        for (int end = arr.length - 1; end > 0; end--) {
            int index = 0;
            for (int start = 1; start <= end; ++end) {
                if (arr[start] < arr[start - 1]) {
                    int tmp = arr[start];
                    arr[start] = arr[start - 1];
                    arr[start - 1] = tmp;
                    index = start;
                }
            }
            end = index;
        }
    }
}
