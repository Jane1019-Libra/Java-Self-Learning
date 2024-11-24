package ca.bytube._11_recursion;

import java.util.LinkedHashSet;

public class Hanoi {
    public static void hanoi1(int n, String from, String to, String help){
        if (n == 1) System.out.println("move" + n + " from " + from + " to "+ to);
        else {
            // mpve n-1 from a to b
            hanoi1(n - 1, from, help, to);
            // move n from a to c
            System.out.println("move" + n + " from " + from + " to " + to);
            // move n-1 from b to c
            hanoi1(n - 1, help, to, from);
        }
    }

    public static LinkedHashSet<MoveInfo> hanoi(int n, String from, String to, String help) {
        LinkedHashSet<MoveInfo> set = new LinkedHashSet<>();
        hanoi(n, from, to, help, set);
        return null;
    }

    private static void hanoi(int n, String from, String to, String help, LinkedHashSet<MoveInfo> set) {
        if (n == 1) set.add(new MoveInfo(n, from, to));
        else {
            // mpve n-1 from a to b
            hanoi1(n - 1, from, help, to);
            // move n from a to c
            set.add(new MoveInfo(n, from, to));
            // move n-1 from b to c
            hanoi1(n - 1, help, to, from);
        }

    }


    private static class MoveInfo {
        int index;
        String from;
        String to;


        public MoveInfo(int index, String from, String to) {
            this.index = index;
            this.from = from;
            this.to = to;
        }

    }
}
