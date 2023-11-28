package cn.com.nadav.algorithm.search.binary;

/**
 * 递归算法 recursion
 * 迭代算法 iteration
 * 二分查找及左边界和右边界查找
 *
 * Java binary search left bound refers to finding the first occurrence of a target value in a sorted array. This can be done using binary search, which has a time complexity of O(log n). Here's an example implementation:
 *
 * ```java
 * public class BinarySearchLeftBound {
 *     public static void main(String[] args) {
 *         int[] arr = {1, 3, 4, 4, 6, 8};
 *         int target = 4;
 *
 *         System.out.println("Left Bound: " + findLeftBound(arr, target));
 *     }
 *
 *     private static int findLeftBound(int[] arr, int target) {
 *         if (arr == null || arr.length == 0) return -1;
 *
 *          // Perform binary search
 *          int left = 0;
 *          int right = arr.length - 1;
 *
 *          while (left <= right) {
 *              int mid = left + (right - left) /2;
 *
 *              if (arr[mid] < target)
 *                  left = mid +1;
 *
 *              else
 *                  right=mid-1;
 *           }
 *
 *           // Check if we found the desired number and 'left' is within bounds.
 *           if(left < arr.length && arr[left] == target)
 *               return left;
 *
 *           return -1; // Target not found in the array
 *      }
 * }
 * ```
 *
 * In this example code snippet:
 * - The `findLeftBound` method takes an integer array `arr` and an integer `target`.
 * - It checks edge cases where there are no elements in the array.
 * - Then it performs a standard binary search with some modifications:
 *    * When comparing values at index `mid`, instead of checking for equality (`arr[mid] == target`), we only check for less than (`arr[mid] < target`) or greater than or equal to (`else`). This ensures that when our loop ends and we didn't find exact match yet but have narrowed down range `[left,right]`, 'left' points to first occurrence of our desired number.
 *    * After exiting from loop ,we need to make sure that 'left' is still within bounds and pointing at correct element before returning its index.
 *
 * This will give you the index of the first occurrence (the "left bound") of your desired number. If it doesn't exist in your input array then `-1` will be returned.
 *
 *
 *
 *
 * Java binary search right bound refers to finding the last occurrence of a target value in a sorted array. This can be done using binary search, which has a time complexity of O(log n). Here's an example implementation:
 *
 * ```java
 * public class BinarySearchRightBound {
 *     public static void main(String[] args) {
 *         int[] arr = {1, 3, 4, 4, 6, 8};
 *         int target = 4;
 *
 *         System.out.println("Right Bound: " + findRightBound(arr, target));
 *     }
 *
 *     private static int findRightBound(int[] arr, int target) {
 *         if (arr == null || arr.length == 0) return -1;
 *
 *          // Perform binary search
 *          int left = 0;
 *          int right = arr.length - 1;
 *
 *          while (left <= right) {
 *              int mid = left + (right - left) /2;
 *
 *              if (arr[mid] > target)
 *                  right=mid-1;
 *
 *              else
 *                  left=mid+1;
 *           }
 *
 *           // Check if we found the desired number and 'right' is within bounds.
 *           if(right >=0 && arr[right] == target)
 *               return right;
 *
 *           return -1; // Target not found in the array
 *      }
 * }
 * ```
 *
 * In this example code snippet:
 * - The `findRightBound` method takes an integer array `arr` and an integer `target`.
 * - It checks edge cases where there are no elements in the array.
 * - Then it performs a standard binary search with some modifications:
 *    * When comparing values at index `mid`, instead of checking for equality (`arr[mid] == target`), we only check for greater than (`arr[mid] > target`) or less than or equal to (`else`). This ensures that when our loop ends and we didn't find exact match yet but have narrowed down range `[left,right]`, 'right' points to last occurrence of our desired number.
 *    * After exiting from loop ,we need to make sure that 'right' is still within bounds and pointing at correct element before returning its index.
 *
 * This will give you the index of the last occurrence (the "right bound") of your desired number. If it doesn't exist in your input array then `-1` will be returned.
 *
 * @author nadav cheung
 * @date 8/16/23
 */
public class BinarySearch {


    /**
     * @param array  有序数组
     * @param target 目标数据
     * @return array index
     */
    public static int recursionSearch(int[] array, int target) {
        return recursionSearch(array, 0, array.length, target);
    }


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
     * 左边界问题      288889｜8
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

        // nums[left,right)
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                right = mid;
            }
            if (nums[mid] < target) {
                left = mid + 1;
            }
            if (nums[mid] > target) {
                right = mid;
            }
        }

        if (left >= nums.length || nums[left] != target) {
            return -1;
        }
        return left;
    }


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


    public static int rightBound(int[] data, int target) {
        if (data.length == 0) {
            return -1;
        }

        int l = 0;
        int r = data.length;

        // data[l,r)
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
        if (r - 1 >= 0 && data[r - 1] == target) {
            return r - 1;
        }
        return -1;
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
