package ca.bytube._17_palindrome;

public class LongestPalindrome {
    public String longestPalindromedp(String s) {
        char [] cs = s.toCharArray();
        if (cs.length <= 1) return s;
        boolean [][] dp = new boolean[cs.length][cs.length];
        String max = "";
        for (int i = cs.length - 1; i >= 0; --i) {
            for (int j = i; j <= cs.length - 1; ++j) {
                int len = j - i + 1;
                dp[i][j] = (cs[i] == cs[j]) && (len <= 2 || dp[i+1][j-1]);
                if (dp[i][j] && max.length() < len) max = s.substring(i, i + len);
            }
        }
        return max;
    }

    public String longestPalindrome(String s) {
        char[] cs = s.toCharArray();
        int maxLen =1;
        int begin = 0;
        /*
        for (int i = cs.length-2; i >= 1; --i) {
            int len1 = palindromeLength(cs, i - 1,i+ 1);
            int len2 = palindromeLength(cs, i , i+1);
            len1 = Math.max(len1, len2);
            if (len1 > maxLen) {
                maxLen = len1;
                begin =  i - ((maxLen - 1) >> 1);
            }
        }
        if (cs[0] == cs[1] && maxLen < 2) {
            begin = 0;
            maxLen = 2;
        }
        */
        int i = 0;
        while (i < cs.length) {
            int l = i-1;
            int r = i;
            while (++r < cs.length && cs[r] == cs[i]);
            i = r;
            while (l >= 0 && r <=cs.length - 1 && cs[l] == cs[r]) {
                l--;
                r++;
            }
            int len = r- ++l;
            if (len > maxLen) {
                maxLen = len;
                begin = l;
            }
        }
        return new String(cs, begin, maxLen);
    }

    private int palindromeLength(char[] cs, int l, int r) {
        while(l >= 0 && r <= cs.length -1 && cs[l] == cs[r]) {
            l--;
            r++;
        }
        return r - l + 1;
    }


    public String longestPalindromManacher(String s) {
        char[] oldChars = s.toCharArray();
        int maxLen = 1;
        char[] cs = preprocess(oldChars);
        int index = 0;
        int[] m = new int[cs.length];
        int c = 0;
        int r = 0;
        for (int i = 2; i <= cs.length - 3; ++i) {
            if (i < r) {
                int li = (c << 1) - i;
                if (i + m[li] <= r) m[i] = m[li];
                else m[i] = r - i;
            }
            while (cs[i+ m[i] + 1] == cs[i - m[i] - 1]) m[i]++;
            if (i + m[i] > r) {
                c = i;
                r = i + m[i];
            }

            if (m[i] > maxLen) {
                maxLen = m[i];
                index = i;
            }

        }
        int begin = (index - m[index]) >> 1;
        return new String(oldChars, begin, maxLen);
    }

    private char[] preprocess(char[] oldChars) {
        char[] cs = new char[(oldChars.length << 1) + 3];
        cs[0] = '^';
        cs[1] = '#';
        cs[cs.length - 1] = '$';
        for (int i = 0; i < oldChars.length; ++i) {
            int index = (i + 1) << 1;
            cs[index] = oldChars[i];
            cs[index  + 1] = '#';
        }
        return cs;
    }
}
