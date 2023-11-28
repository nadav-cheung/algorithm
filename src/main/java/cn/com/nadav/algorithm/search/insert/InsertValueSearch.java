package cn.com.nadav.algorithm.search.insert;

/**
 * @author nadav cheung
 * @date 8/16/23
 */
public class InsertValueSearch {


    // 二分搜索的进阶版本 只适用于平均分布的数据

    public static int search(int[] nums, int target) {
        return search(nums, 0, nums.length, target);
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
