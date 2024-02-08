package cn.com.nadav.algorithm.sort;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * email nadav.cheung@gmail.com
 * date 2024-02-07 23:40:23
 * description
 *
 * @author Nadav Cheung
 * @since 1.0.x
 */
public class SelectionSortTest {

    // Tests for selectionSort with int arrays
    @Test
    void testSelectionSortIntNormalCase() {
        int[] nums = {5, 3, 4, 1, 2};
        SelectionSort.selectionSort(nums);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, nums);
    }

    @Test
    void testSelectionSortIntAlreadySorted() {
        int[] nums = {1, 2, 3, 4, 5};
        SelectionSort.selectionSort(nums);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, nums);
    }

    @Test
    void testSelectionSortIntReverseSorted() {
        int[] nums = {5, 4, 3, 2, 1};
        SelectionSort.selectionSort(nums);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, nums);
    }

    @Test
    void testSelectionSortIntEmptyArray() {
        int[] nums = {};
        SelectionSort.selectionSort(nums);
        assertArrayEquals(new int[]{}, nums);
    }

    @Test
    void testSelectionSortIntSingleElement() {
        int[] nums = {1};
        SelectionSort.selectionSort(nums);
        assertArrayEquals(new int[]{1}, nums);
    }

    @Test
    void testSelectionSortIntWithDuplicates() {
        int[] nums = {5, 2, 4, 2, 5};
        SelectionSort.selectionSort(nums);
        assertArrayEquals(new int[]{2, 2, 4, 5, 5}, nums);
    }

    // Tests for selectionSort with generic arrays
    @Test
    void testSelectionSortGenericNormalCase() {
        Integer[] nums = {5, 3, 4, 1, 2};
        SelectionSort.selectionSort(nums);
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, nums);
    }

    // Additional tests for generic types can be added following the pattern of the int array tests
}
