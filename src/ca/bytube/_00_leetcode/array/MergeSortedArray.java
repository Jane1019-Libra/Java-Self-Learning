package ca.bytube._00_leetcode.array;

public class MergeSortedArray {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int index1 = m - 1;
        int index2 = n - 1;
        int current = m + n - 1;
        while(current >= 0) {
            int val = 0;
            if (index2 >= 0 && index1 >= 0) {
                if (nums1[index1] >= nums2[index2]) nums1[current--] = nums1[index1--];
                else nums1[current--] = nums1[index2--];
            } else if (index1 >= 0) nums1[current--] = nums1[index1--];
             else nums1[current--]  = nums2[index2--];
        }
    }
}
