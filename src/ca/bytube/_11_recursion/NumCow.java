package ca.bytube._11_recursion;

public class NumCow {
    int NumCow0(int n) {
        if (n <= 4) return n + 1;
        return NumCow0(n-1) + NumCow0(n-4);
    }

    int NumCow1(int n) {
        int [] arr = new int[n+1];
        arr[1] = 2;
        arr[2] = 3;
        arr[3] = 4;
        arr[4] = 5;
        for (int i = 5; i <= n; ++i) arr[i] = arr[i-1] + arr[i-4];
        return arr[n];
    }

    int NumCow2(int n) {
        int [] arr = new int[n+1];
        arr[1] = 2;
        arr[2] = 3;
        arr[3] = 4;
        arr[4] = 5;
        return numofCow(n, arr);
    }

    private static int numofCow(int n, int[] array) {
        if (array[n] == 0) array[n] = array[n-1] + array[n-4];
        return array[n];
    }
}