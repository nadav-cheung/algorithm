package cn.com.nadav.algorithm.sort;

public class SelectionSort {

    /**
     * 宏观前提  数组左闭右开
     * i 表示进行的第i轮排序
     * nums[0,i)已经排序
     * nums[i,n)未排序
     * 每轮排序从未排序的列表中选择最小的元素，放在未排序的列表起始位置i上
     *
     * @param nums 待排序数组
     */
    public static void selectionSort(int[] nums) {
        int len = nums.length;

        for (int i = 0; i < len; i++) {
            // 记录未排序列表中最小元素下标
            int minIndex = i;
            for (int j = i; j < len; j++) {
                if (nums[minIndex] > nums[j]) {
                    minIndex = j;
                }
            }
            swap(nums, i, minIndex);
        }
    }

    private static void swap(int[] nums, int low, int high) {
        int temp = nums[low];
        nums[low] = nums[high];
        nums[high] = temp;
    }

    public static <E extends Comparable<? super E>> void selectionSort(E[] nums) {
        int len = nums.length;

        for (int i = 0; i < len; i++) {
            int minIndex = i;
            for (int j = i; j < len; j++) {
                if (nums[minIndex].compareTo(nums[j]) > 0) {
                    minIndex = j;
                }
            }
            swap(nums, minIndex, i);
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
