package cn.com.nadav.algorithm.search.linear;

/**
 * @author nadav cheung
 * @date 8/16/23
 */
public class LinearSearch {
    /**
     * Searches for an integer target in an array of integers.
     *
     * @param nums   the array of integers
     * @param target the target integer to search for
     * @return the index of the target in the array, or -1 if not found
     */
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

    /**
     * Searches for an element target in an array of elements.
     *
     * @param nums   the array of elements
     * @param target the target element to search for
     * @param <E>    the type of the elements in the array
     * @return the index of the target in the array, or -1 if not found
     */
    public static <E extends Comparable<? super E>> int search(E[] nums, E target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i].compareTo(target) == 0) {
                return i;
            }
        }
        return -1;
    }
}