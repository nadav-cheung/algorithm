 递归算法 recursion
 迭代算法 iteration
 二分查找及左边界和右边界查找

 Java binary search left bound refers to finding the first occurrence of a target value in a sorted array. This can be done using binary search, which has a time complexity of O(log n). Here's an example implementation:

 ```java
 public class BinarySearchLeftBound {
     public static void main(String[] args) {
         int[] arr = {1, 3, 4, 4, 6, 8};
         int target = 4;

         System.out.println("Left Bound: " + findLeftBound(arr, target));
     }

     private static int findLeftBound(int[] arr, int target) {
         if (arr == null || arr.length == 0) return -1;

          // Perform binary search
          int left = 0;
          int right = arr.length - 1;

          while (left <= right) {
              int mid = left + (right - left) /2;

              if (arr[mid] < target)
                  left = mid +1;

              else
                  right=mid-1;
           }

           // Check if we found the desired number and 'left' is within bounds.
           if(left < arr.length && arr[left] == target)
               return left;

           return -1; // Target not found in the array
      }
 }
 ```

 In this example code snippet:
 - The `findLeftBound` method takes an integer array `arr` and an integer `target`.
 - It checks edge cases where there are no elements in the array.
 - Then it performs a standard binary search with some modifications:
     When comparing values at index `mid`, instead of checking for equality (`arr[mid] == target`), we only check for less than (`arr[mid] < target`) or greater than or equal to (`else`). This ensures that when our loop ends and we didn't find exact match yet but have narrowed down range `[left,right]`, 'left' points to first occurrence of our desired number.
     After exiting from loop ,we need to make sure that 'left' is still within bounds and pointing at correct element before returning its index.

 This will give you the index of the first occurrence (the "left bound") of your desired number. If it doesn't exist in your input array then `-1` will be returned.




 Java binary search right bound refers to finding the last occurrence of a target value in a sorted array. This can be done using binary search, which has a time complexity of O(log n). Here's an example implementation:

 ```java
 public class BinarySearchRightBound {
     public static void main(String[] args) {
         int[] arr = {1, 3, 4, 4, 6, 8};
         int target = 4;

         System.out.println("Right Bound: " + findRightBound(arr, target));
     }

     private static int findRightBound(int[] arr, int target) {
         if (arr == null || arr.length == 0) return -1;

          // Perform binary search
          int left = 0;
          int right = arr.length - 1;

          while (left <= right) {
              int mid = left + (right - left) /2;

              if (arr[mid] > target)
                  right=mid-1;

              else
                  left=mid+1;
           }

           // Check if we found the desired number and 'right' is within bounds.
           if(right >=0 && arr[right] == target)
               return right;

           return -1; // Target not found in the array
      }
 }
 ```

 In this example code snippet:
 - The `findRightBound` method takes an integer array `arr` and an integer `target`.
 - It checks edge cases where there are no elements in the array.
 - Then it performs a standard binary search with some modifications:
     When comparing values at index `mid`, instead of checking for equality (`arr[mid] == target`), we only check for greater than (`arr[mid] > target`) or less than or equal to (`else`). This ensures that when our loop ends and we didn't find exact match yet but have narrowed down range `[left,right]`, 'right' points to last occurrence of our desired number.
     After exiting from loop ,we need to make sure that 'right' is still within bounds and pointing at correct element before returning its index.

 This will give you the index of the last occurrence (the "right bound") of your desired number. If it doesn't exist in your input array then `-1` will be returned.
