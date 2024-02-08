package cn.com.nadav.algorithm.sort;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * email nadav.cheung@gmail.com
 * date 2024-02-07 23:35:25
 * description
 *
 * @author Nadav Cheung
 * @since 1.0.x
 */
public class HeapSortTest {

    private final HeapSort<Integer> heapSort = new HeapSort<>();

    @Test
    void testHeapSortNormalCase() {
        Integer[] nums = {4, 2, 7, 1, 3};
        heapSort.sort(nums);
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 7}, nums);
    }

    @Test
    void testHeapSortAlreadySorted() {
        Integer[] nums = {1, 2, 3, 4, 5};
        heapSort.sort(nums);
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, nums);
    }

    @Test
    void testHeapSortReverseSorted() {
        Integer[] nums = {5, 4, 3, 2, 1};
        heapSort.sort(nums);
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, nums);
    }

    @Test
    void testHeapSortEmptyArray() {
        Integer[] nums = {};
        heapSort.sort(nums);
        assertArrayEquals(new Integer[]{}, nums);
    }

    @Test
    void testHeapSortSingleElement() {
        Integer[] nums = {1};
        heapSort.sort(nums);
        assertArrayEquals(new Integer[]{1}, nums);
    }

    @Test
    void testHeapSortWithDuplicates() {
        Integer[] nums = {4, 2, 4, 1, 2};
        heapSort.sort(nums);
        assertArrayEquals(new Integer[]{1, 2, 2, 4, 4}, nums);
    }
}
