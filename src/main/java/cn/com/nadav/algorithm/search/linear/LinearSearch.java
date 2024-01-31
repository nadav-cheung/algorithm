package cn.com.nadav.algorithm.search.linear;

/**
 * @author nadav cheung
 * @date 8/16/23
 */
public class LinearSearch {

    public static int search(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static <E extends Comparable<? super E>> int search(E[] nums, E target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i].compareTo(target) == 0) {
                return i;
            }
        }
        return -1;
    }


}
