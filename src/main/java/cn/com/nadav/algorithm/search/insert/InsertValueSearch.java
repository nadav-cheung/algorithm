package cn.com.nadav.algorithm.search.insert;

/**
 * InsertValueSearch.java
 *
 * This program implements the Insert Value Search algorithm, which is an advanced version of binary search.
 * It is suitable for searching in data that is evenly distributed.
 *
 * The main method 'search' performs the insert value search on a given sorted array 'nums' to find the target value.
 * The method 'interpolationSearch' implements the insert value search algorithm recursively.
 * The method 'search' is a helper method that performs boundary checks and calls the 'interpolationSearch' method.
 *
 * @author nadav cheung
 * @date 8/16/23
 */
public class InsertValueSearch {


    // 二分搜索的进阶版本 只适用于平均分布的数据

    public static int search(int[] nums, int target) {
        return search(nums, 0, nums.length, target);
    }

    /**
     * Performs insert value search on a sorted array.
     *
     * @param arr    The sorted array to be searched
     * @param low    The lower index of the search range
     * @param high   The upper index of the search range
     * @param target The target value to be searched
     * @return       The index of the target value in the array, or -1 if not found
     */
    public static int interpolationSearch(int[] arr, int low, int high, int target) {
        if (low <= high && target >= arr[low] && target <= arr[high]) {

            // Avoid division by zero if arr[high] is equal to arr[low]
            if (arr[high] == arr[low]) {
                return (arr[low] == target) ? low : -1; // If arr[low] is the target, return low, else target not found
            }

            // 计算插值位置
            int pos = low + ((target - arr[low]) * (high - low)) / (arr[high] - arr[low]);

            if (arr[pos] == target) {
                return pos;
            }

            if (arr[pos] < target) {
                return interpolationSearch(arr, pos + 1, high, target);
            } else {
                return interpolationSearch(arr, low, pos - 1, target);
            }
        }
        return -1;
    }


    private static int search(int[] nums, int l, int r, int target) {
        // 越界条件
        //注意：target < arr[0]  和  target > arr[arr.length - 1] 必须需要
        //否则我们得到的 mid 可能越界
        if (l >= r || target < nums[0] || target > nums[nums.length - 1]) {
            return -1;
        }

        // 自适应
        int mid = l + (r - l) * (target - nums[l]) / (nums[r] - nums[l]);
        int midVal = nums[mid];

        // 说明应该向右边递归
        if (target > midVal) {
            return search(nums, mid + 1, r, target);
        } else if (target < midVal) {
            // 说明向左递归查找
            return search(nums, l, mid, target);
        } else {
            return mid;
        }
    }
}
