package ca.bytube._11_recursion;

public class PrintAllSubstring {
    public static void main(String[] args) {

    }

    public static void printAllSubstring(String s) {
        String str = "";
        printAllSubstring(s, 0, str);
    }

    private static void printAllSubstring(String s, int index, String str) {
        if (index == s.length()) return;
        printAllSubstring(s, index + 1, str);
        printAllSubstring(s, index+1, str + s.charAt(index));
    }
}
