package ca.bytube._13_greedy;

import java.util.Arrays;

public class Pirates {

    public int maxNumofAntiques(int capacity, int[] weights) {
        int count = 0;
        Arrays.sort(weights);
        for (int each : weights) {
            if (capacity >= 0) {
                count++;
                capacity -= each;
            }
        }
        return 0;
    }
}
