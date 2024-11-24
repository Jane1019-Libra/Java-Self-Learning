package ca.bytube._13_greedy;

import java.util.Arrays;
import java.util.Comparator;

public class BestArrange {
    private static class Meeting{
        int start;
        int end;

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static int bestArrange(Meeting[] meetings, int current) {
        Arrays.sort(meetings, Comparator.comparingInt(o->o.end));

        int count = 0;
        for (int i = 0; i < meetings.length; i++) {
            if (current <= meetings[i].start) {
                count++;
                current = meetings[i].end;
            }
        }
        return count;
    }
}
