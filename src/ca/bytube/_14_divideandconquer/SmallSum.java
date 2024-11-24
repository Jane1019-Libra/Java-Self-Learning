package ca.bytube._14_divideandconquer;

public class SmallSum {

    public static void main(String[] args) {
        int[] a = new int[]{1,3,4,2,5};
        mergeSort(a);


    }

    public static int mergeSort(int[] arr) {
        if (arr.length == 1) return 0;
        else return mergeSort(arr, 0, arr.length);
    }

    public static int mergeSort(int[] arr, int l, int r) {
        if (l == r) return 0;
        int mid = (l + r) >> 1;
        return mergeSort(arr, l, mid) + mergeSort(arr, mid + 1, r) + merge(arr, l, mid, r);
    }

    private static int merge(int[] arr, int l, int mid, int r) {
        int p1 = l;
        int p2 = mid + 1;
        int [] help = new int[r-l+1];
        int i = 0;
        int sum = 0;
        while (p1 <= mid && p2 <= r) {
            sum += arr[p1] < arr[p2] ? (r-p2+1) * arr[p1] : 0;
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) help[i++] = arr[p1++];
        while (p2 <= mid) help[i++] = arr[p2++];
        for (int j = 0; j < help.length; ++j) arr[j+l] = help[j];
        return sum;
    }

}
