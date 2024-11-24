package ca.bytube._13_greedy;

import java.util.Arrays;

public class ChangeCoins {
    public static int changeCoins(int[] faces, int money) {
        int counter = 0;
        Arrays.sort(faces);
        for (int i = faces.length - 1; i >= 0; --i) {
            if (money < faces[i]) continue;
            counter++;
            money -= faces[i];
            i++;

        }
        return counter;
    }
}
