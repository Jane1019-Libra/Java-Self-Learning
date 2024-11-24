package ca.bytube._16_sequence;

import java.util.Arrays;

public class BruteForce {
    public static int indexOf(String text, String pattern) {
        char[] textChars = text.toCharArray();
        char[] patternChars = pattern.toCharArray();
        int plen = patternChars.length;
        int tlen = textChars.length;
        int pi = 0;
        int ti = 0;
        int len = tlen - plen;
        while (pi < plen && ti <= len) {
            if (textChars[ti] == patternChars[pi]) {
                ti++;
                pi++;
            } else {
                pi = 0;
                ti -= pi + 1;
            }
        }
        return pi == plen ? ti - pi : -1;
    }

    public static int indexOf2(String text, String pattern) {
        char[] textChars = text.toCharArray();
        char[] patternChars = pattern.toCharArray();
        int plen = patternChars.length;
        int tlen = textChars.length;
        int pi = 0;
        int ti = 0;
        int[] next = next(pattern);
        int len = tlen - plen;
        while (pi < plen && ti <= len) {
            if (pi < 0 || textChars[ti] == patternChars[pi]) {
                ti++;
                pi++;
            } else {
                pi = next[pi];
            }
        }
        return pi == plen ? ti - pi : -1;
    }

    private static int[] next(String pattern) {
        int len = pattern.length();
        char[] p = pattern.toCharArray();
        int[] next = new int[len];
        int i = 0;
        int n = -1;
        next[0] = -1;
        while (i < p.length - 1) {
            if (n < 0 || p[i] == p[n]) next[++i] = ++n;
            else {
                n = next[n];
            }
        }
        return next;
    }
}
