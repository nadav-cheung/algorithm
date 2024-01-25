package cn.com.nadav.algorithm.sort;

import java.util.Arrays;

public class HeapSort<T extends Comparable<? super T>> {
    private static int leftChild(int k) {
        return (k << 1) + 1;
    }

    // 测试代码
    public static void main(String args[]) {
        Integer[] intArray = {12, 11, 13, 5, 6, 7};
        HeapSort<Integer> intSorter = new HeapSort<>();
        intSorter.sort(intArray);
        System.out.println("Sorted Integer array: " + Arrays.toString(intArray));

        String[] stringArray = {"banana", "orange", "apple", "grape", "lemon"};
        HeapSort<String> stringSorter = new HeapSort<>();
        stringSorter.sort(stringArray);
        System.out.println("Sorted String array: " + Arrays.toString(stringArray));
    }

    public void sort(T[] nums) {
        int length = nums.length;

        // 构建最大堆
        heapify(nums, length);

        // 逐个从堆顶取出元素放到数组末尾，完成排序
        for (int i = length - 1; i > 0; i--) {
            // 队尾元素
            T heapTail = nums[i];

            // 取出堆顶元素 放入堆尾
            T heapHead = nums[0];
            nums[i] = heapHead;

            // 在堆定插入队尾元素 这时候会违反堆的元素 执行siftDown
            siftDownComparable(0, heapTail, nums, i);
        }
    }

    private void heapify(T[] nums, int length) {
        int i = (length >>> 1) - 1;
        for (; i >= 0; i--)
            siftDownComparable(i, nums[i], nums, length);
    }

    /**
     * 在位置 k 处插入项 x，通过将 x 沿着树重复降级直到它小于或等于其子项或者是叶子来保持堆不变性。
     */
    private void siftDownComparable(int k, T x, T[] nums, int length) {
        int half = length >>> 1;           // loop while a non-leaf

        // 将 x 沿着树重复降级直到它大于或等于其子项或者是叶子来保持堆不变性。
        while (k < half) {
            // 记录两个孩子节点中的较大节点
            int child = leftChild(k); // assume left child is least

            // 记录 两个孩子节点中的较大者
            T c = nums[child];

            // 右边孩子节点
            int right = child + 1;

            // 右边孩子节点存在 并且 左边孩子节点大于右边孩子节点 寻找两个孩子节点中的较小者
            if (right < length && c.compareTo(nums[right]) < 0)
                // 更新孩子节点和索引
                c = nums[child = right];

            // key小于两个孩子节点 满足堆的性质
            if (x.compareTo(c) > 0)
                break;

            // 不满足性质 在k的位置插入较大的孩子节点
            nums[k] = c;

            // x 沿着树降级 将k更新为较大的孩子节点 在k处重新插入x
            k = child;
        }
        // 跳出循环 在位置 k 处插入项 x，
        nums[k] = x;
    }

}
