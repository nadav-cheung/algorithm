package cn.com.nadav.algorithm.search.binary;

/**
 * 递归算法 recursion
 * 迭代算法 iteration
 * 二分查找及左边界和右边界查找
 *
 * @author nadav cheung
 * @date 8/16/23
 */
public class BinarySearch {


    /**
     * 二分搜索（又称二分查找）是一种在有序数组中查找特定元素的算法
     *
     * @param array  有序数组
     * @param target 目标数据
     * @return array index 不存在则返回-1
     */
    public static int recursionSearch(int[] array, int target) {
        return recursionSearch(array, 0, array.length, target);
    }


    /**
     * @param array  数组
     * @param l      左边界
     * @param r      右边界 初始化时等于数组长度  array[l,r)左闭右开区间
     * @param target
     * @return
     */
    private static int recursionSearch(int[] array, int l, int r, int target) {

        // array[l,r) 在左闭右开区间中寻找 target
        if (l >= r) {
            return -1;
        }
        int mid = l + (r - l) / 2;

        if (array[mid] == target) {
            return mid;
        }

        if (array[mid] < target) {
            return recursionSearch(array, mid + 1, r, target);
        }

        // it means array[mid] > target
        return recursionSearch(array, l, mid, target);
    }

    // Java范型接口
    public static <E extends Comparable<? super E>> int recursionSearch(E[] array, E target) {
        return recursionSearch(array, 0, array.length, target);
    }


    public static <E extends Comparable<? super E>> int recursionSearch(E[] array, int l, int r, E target) {
        if (l >= r) {
            return -1;
        }

        int mid = l + (r - l) / 2;

        if (array[mid].compareTo(target) == 0) {
            return mid;
        }

        if (array[mid].compareTo(target) < 0) {
            return recursionSearch(array, mid + 1, r, target);
        }

        return recursionSearch(array, l, mid, target);
    }


    /**
     * 迭代实现
     */
    public static int iterationSearch(int[] array, int target) {
        int l = 0;
        int r = array.length;
        // 在 array[l,r）中寻找target
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (array[mid] == target) {
                return mid;
            } else if (array[mid] < target) {
                l = mid + 1;
            } else if (array[mid] > target) {
                r = mid;
            }
        }
        return -1;
    }


    public static <E extends Comparable<? super E>> int iterationSearch(E[] array, E target) {
        int l = 0;
        int r = array.length;

        // 在 array[l,r）中寻找target  l>=r 跳出
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (array[mid].compareTo(target) == 0) {
                return mid;
            }
            if (array[mid].compareTo(target) < 0) {
                l = mid + 1;
            }
            if (array[mid].compareTo(target) > 0) {
                r = mid;
            }
        }
        return -1;
    }


    /**
     * Java binary search left bound refers to finding the first occurrence of a target value in a sorted array.
     * This can be done using binary search, which has a time complexity of O(log n). Here's an example implementation:
     * 在一个可重复的有序数组中寻找第一次出现的元素
     * 不断向左边逼近
     *
     * @param nums
     * @param target
     * @return
     */
    public static int leftBound(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int left = 0;
        int right = nums.length;

        // nums[left,right) 左闭右开区间查询
        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }

        }

        //  // Check if we found the desired number and 'left' is within bounds. 越界问题分析
        if (left >= nums.length || nums[left] != target) {
            return -1;
        }
        return left;
    }

    private static int findLeftBound(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;

        // Perform binary search
        int left = 0;
        int right = nums.length - 1;

        // nums[l,r] 左闭右闭区间
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] < target)
                left = mid + 1;
            else
                right = mid - 1;
        }

        // Check if we found the desired number and 'left' is within bounds.
        if (left < nums.length && nums[left] == target)
            return left;

        return -1; // Target not found in the array
    }


    /**
     * * 在一个可重复的有序数组中寻找第一次出现的元素
     * * 不断向左边逼近
     * * 左边界问题      288889｜8
     *
     * @param nums
     * @param target
     * @param <E>
     * @return
     */
    public static <E extends Comparable<? super E>> int leftBound(E[] nums, E target) {
        if (nums.length == 0) {
            return -1;
        }

        int l = 0;
        int r = nums.length;

        // nums[l,r) l>=r 跳出
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid].compareTo(target) == 0) {
                r = mid;
            }
            if (nums[mid].compareTo(target) < 0) {
                l = mid + 1;
            }
            if (nums[mid].compareTo(target) > 0) {
                r = mid;
            }
        }
        if (l >= nums.length || nums[l].compareTo(target) != 0) {
            return -1;
        }
        return l;
    }

    /**
     * * Java binary search right bound refers to finding the last occurrence of a target value in a sorted array.
     * This can be done using binary search, which has a time complexity of O(log n). Here's an example implementation:
     *
     * @param data
     * @param target
     * @return
     */

    public static int rightBound(int[] data, int target) {
        if (data == null || data.length == 0) {
            return -1;
        }

        int l = 0;
        int r = data.length;

        // data[l,r) 左闭右开区间
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (data[mid] == target) {
                l = mid + 1;
            }
            if (data[mid] < target) {
                l = mid + 1;
            }
            if (data[mid] > target) {
                r = mid;
            }
        }

        // l>=r 跳出条件

        if (r - 1 >= 0 && data[r - 1] == target) {
            return r - 1;
        }
        return -1;
    }


    private static int findRightBound(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;

        // Perform binary search
        int left = 0;
        int right = nums.length - 1;

        // nums[l,r] 左闭右闭区间
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] > target)
                right = mid - 1;
            else
                left = mid + 1;
        }

        // Check if we found the desired number and 'right' is within bounds.
        if (right >= 0 && nums[right] == target)
            return right;

        return -1; // Target not found in the array
    }


    public static <E extends Comparable<? super E>> int rightBound(E[] nums, E target) {
        if (nums.length == 0) {
            return -1;
        }

        int l = 0;
        int r = nums.length;

        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid].compareTo(target) == 0) {
                l = mid + 1;
            }
            if (nums[mid].compareTo(target) < 0) {
                l = mid + 1;
            }
            if (nums[mid].compareTo(target) > 0) {
                r = mid;
            }
        }
        if (r - 1 > 0 && nums[r - 1].compareTo(target) == 0) {
            return r - 1;
        }
        return -1;
    }


    public static void main(String[] args) {
        int leftBound = rightBound(new int[]{2, 3, 4, 5, 8, 8, 8, 9, 10}, 8);
        System.out.println(leftBound);
    }


}
