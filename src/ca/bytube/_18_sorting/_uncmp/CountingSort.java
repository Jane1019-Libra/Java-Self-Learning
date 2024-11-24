package ca.bytube._18_sorting._uncmp;

public class CountingSort {
    public static void sort(Integer[] array) {
        if (array == null || array.length == 0 || array.length == 1) return;
        int max = array[0];
        int min = array[0];
        for (int i = 1; i < array.length; ++i) {
            if (array[i] > max) max = array[i];
            if (array[i] < min) min = array[i];
        }

        int[] counts = new int [max - min + 1];
        for (Integer integer : array) counts[integer - min]++;

        for (int i = 1; i < array.length; ++i) {
            counts[i] = counts[i] + counts[i-1];
        }

        int[] newArr = new int[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            newArr[--counts[array[i] - min]] = array[i];
        }
    }
}
