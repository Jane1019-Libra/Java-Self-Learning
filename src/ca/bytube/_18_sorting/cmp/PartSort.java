package ca.bytube._18_sorting.cmp;

public class PartSort {
    public static void main(String[] args) {
        PartSort.sort(new int[]{1,5,4,3,2,6,7,2,8});
    }

    public static void sort(int[] nums) {
        int l = -1;
        int min = nums[nums.length - 1];
        for (int cur = nums.length - 2; cur >= 0; cur--) {
            if (nums[cur] <= min) {
                min = nums[cur];
            } else {
                l = cur;
            }
        }
        int r = -1;
        int max = nums[0];
        for (int cur = 1; cur < nums.length - 1; ++cur) {
            if (nums[cur] >= max) max = nums[cur];
            else r = cur;
        }
    }
}
