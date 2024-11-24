package ca.bytube._11_recursion;


/*
https://leetcode.com/problems/fibonacci-number/description/

 */
public class Fib {
    int[] arr;
    public Fib(int n) {
        arr = new int[n+1];
    }

    public int fib3(int n) {
        if (n < 0 || n > 30) throw new RuntimeException("illegal argument!");
        if (n <= 1) return n;
        arr[1] = 1;
        arr[2] = 1;
        return arr[n];
    }

    // first
    // wasting time
    public int fib0(int n) {
        if (n < 0 || n > 30) throw new RuntimeException("illegal argument!");
        if (n <= 1) return n;
        return fib0(n-1) + fib0(n-2);
    }

    //存结果 空间换时间
    public int fib1(int n) {
        if (n < 0 || n > 30) throw new RuntimeException("illegal argument!");
        if (n <= 1) return n;
        int[] arr = new int[n+1];
        arr[1] = 1;
        arr[2] = 1;
        for (int i = 3; i <=n; ++i) arr[i] = arr[i-1] + arr[i-2];
        return arr[n];
    }

    // same with fib1 but using recusion
    public int fib2(int n) {
        if (n < 0 || n > 30) throw new RuntimeException("illegal argument!");
        if (n <= 1) return n;
        int[] arr = new int[n+1];
        arr[1] = 1;
        arr[2] = 1;
        return fib2(n, arr);
    }

    public int fib2(int n, int[] arr) {
        if (arr[n] == 0) {
            arr[n] = fib2(n - 1, arr) + fib2(n - 2, arr);
        }
        return arr[n];
    }


    // 滚动数组
    public int fib(int n) {
        if (n < 0 || n > 30) throw new RuntimeException("illegal argument!");
        if (n <= 1) return n;

        int[] arr = new int[2];
        arr[0] = 1;
        arr[1] = 1;
        for (int i = 2; i < n; ++i) {
            arr[i & 1] = arr[(i-1) & 1] + arr[(i-2) & 1];
        }
        return arr[(n - 1) & 1];
    }

    public int fib5(int n) {
        if (n < 0 || n > 30) throw new RuntimeException("illegal argument!");
        if (n <= 1) return n;

        int first = 0;
        int second = 1;
        int sum = 0;
        for (int i = 0; i < n - 1; ++i) {
            sum = first + second;
            first = second;
            second = sum;
        }
        return second;
    }

    public int fib6(int n) {
        if (n < 0 || n > 30) throw new RuntimeException("illegal argument !");
        if (n <= 1) return n;
        return fib6(n, 0, 1);
    }
    public int fib6(int n, int first, int second) {
        if (n <= 1) return second;
        return fib6(n-1, second, first + second);
    }

}
