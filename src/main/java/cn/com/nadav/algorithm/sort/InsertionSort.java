package cn.com.nadav.algorithm.sort;

public class InsertionSort {


    private InsertionSort() {

    }

    /**
     * 宏观前提  数组左闭右开
     * i 表示已经排序待数组的长度
     * nums[0,i)已经排序
     * nums[i,n)未排序
     * 每轮排序选择未排序中的第一个元素插入到已经排序的数组的合适位置中，使之成为新的已排序数组
     *
     * @param nums 待排序待数组
     */
    public static void insertionSort(int[] nums) {
        int len = nums.length;
        // 当一个数组中只有一个元素，那么这个数组天然就是已经排序的数组
        // nums[0，1）是已经排序的数组
        for (int i = 1; i < len; i++) {
            // 未排序列表中的第一个元素，待排序元素
            int ret = nums[i];

            // 将待排序元素插入已经排序的数组nums[0,i)中
            // 使数组nums[0,i+1)成为新的已经排序数组
            int j;
            for (j = i; j - 1 >= 0 && ret < nums[j - 1]; j--) {
                nums[j] = nums[j - 1];
            }
            nums[j] = ret;
        }
    }

    public static <E extends Comparable<? super E>> void insertionSort(E[] nums) {
        for (int i = 1; i < nums.length; i++) {
            // 代排序代元素
            E ret = nums[i];
            int j;
            for (j = i; j - 1 >= 0 && ret.compareTo(nums[j - 1]) < 0; j--) {
                nums[j] = nums[j - 1];
            }
            nums[j] = ret;
        }
    }
}
