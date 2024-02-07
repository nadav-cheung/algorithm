package cn.com.nadav.algorithm.sort;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
/**
 * email nadav.cheung@gmail.com
 * date 2024-02-07 23:37:15
 * description
 *
 * @author Nadav Cheung
 * @since 1.0.x
 */
public class InsertionSortTest {

    // Tests for insertionSort with int arrays
    @Test
    void testInsertionSortIntNormalCase() {
        int[] nums = {5, 3, 4, 1, 2};
        InsertionSort.insertionSort(nums);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, nums);
    }

    @Test
    void testInsertionSortIntWithDuplicates() {
        int[] nums = {5, 2, 4, 2, 5};
        InsertionSort.insertionSort(nums);
        assertArrayEquals(new int[]{2, 2, 4, 5, 5}, nums);
    }

    @Test
    void testInsertionSortIntAlreadySorted() {
        int[] nums = {1, 2, 3, 4, 5};
        InsertionSort.insertionSort(nums);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, nums);
    }

    @Test
    void testInsertionSortIntReverseSorted() {
        int[] nums = {5, 4, 3, 2, 1};
        InsertionSort.insertionSort(nums);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, nums);
    }

    @Test
    void testInsertionSortIntEmptyArray() {
        int[] nums = {};
        InsertionSort.insertionSort(nums);
        assertArrayEquals(new int[]{}, nums);
    }

    @Test
    void testInsertionSortIntSingleElement() {
        int[] nums = {1};
        InsertionSort.insertionSort(nums);
        assertArrayEquals(new int[]{1}, nums);
    }

    // Tests for insertionSort with generic arrays
    @Test
    void testInsertionSortGenericNormalCase() {
        Integer[] nums = {5, 3, 4, 1, 2};
        InsertionSort.insertionSort(nums);
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, nums);
    }

    // Add more tests for generic types similar to the int array tests above
}
