
package cn.com.nadav.algorithm.sort;

/**
 * BubbleSort is a class that provides methods for sorting arrays using the Bubble Sort algorithm.
 * The bubbleSort method can be used to sort an array of integers in ascending order.
 * The bubbleSort method can also be used to sort an array of objects that implement the Comparable interface in ascending order.
 */


public class BubbleSort {

    /**
     * 宏观前提  数组左闭右开
     * i 表示进行的第i轮排序
     * nums[0,n-i)未排序
     * nums[n-1,n)已经排序
     * 每轮排序选择未排序中最大的元素放在nums[n-i-1]位置
     * 每轮排序结束  nums[n-i-1]位置 元素确认
     *
     * @param nums The array of integers to be sorted.
     */
    public static void bubbleSort(int[] nums) {
        int len = nums.length;
        // 每轮排序都将能确定至少一个元素的位置，
        // 共有n个元素，当n-1个元素的位置已经确定，
        // 剩下的一个元素位置必将确认
        // 所以只需要进行n-1轮排序
        for (int i = 0; i < len - 1; i++) {
            // 针对每一轮排序 我们只做一件事
            // 1、在nums[n-i-1]的位置上放上正确的数据
            for (int j = 0; j < len - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                }
            }
        }
    }

    private static void swap(int[] nums, int low, int high) {
        int temp = nums[low];
        nums[low] = nums[high];
        nums[high] = temp;
    }

    public static <E extends Comparable<? super E>> void bubbleSort(E[] nums) {
        int len = nums.length;
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - i - 1; j++) {
                if (nums[j].compareTo(nums[j + 1]) > 0) {
                    swap(nums, j, j + 1);
                }
            }
        }
    }

    private static <E extends Comparable<? super E>> void swap(E[] nums, int low, int high) {
        E temp = nums[low];
        nums[low] = nums[high];
        nums[high] = temp;
        // help gc
        temp = null;
    }
}
