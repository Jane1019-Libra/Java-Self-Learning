package ca.bytube._14_divideandconquer;

public class GetMaxFromArray {
    public static int getMaxFromArray(int [] array) {
        if (array.length == 1) return array[0];
        return getMaxFromArray(array, 0, array.length - 1);
    }

    public static int getMaxFromArray(int[] array, int left, int right) {
        if (right == left) return array[left];
        int mid = (left + right) >> 1;
        return Math.max(getMaxFromArray(array, left, mid), getMaxFromArray(array, mid + 1, right));
    }
}
