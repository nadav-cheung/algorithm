package cn.com.nadav.algorithm.sort;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * email nadav.cheung@gmail.com
 * date 2024-02-07 23:32:21
 * description
 *
 * @author Nadav Cheung
 * @since 1.0.x
 */
public class BubbleSortTest {

    // Test cases for bubbleSort with int arrays
    @Test
    void testBubbleSortIntNormalCase() {
        int[] nums = {4, 2, 7, 1, 3};
        BubbleSort.bubbleSort(nums);
        assertArrayEquals(new int[]{1, 2, 3, 4, 7}, nums);
    }

    @Test
    void testBubbleSortIntAlreadySorted() {
        int[] nums = {1, 2, 3, 4, 5};
        BubbleSort.bubbleSort(nums);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, nums);
    }

    @Test
    void testBubbleSortIntReverseSorted() {
        int[] nums = {5, 4, 3, 2, 1};
        BubbleSort.bubbleSort(nums);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, nums);
    }

    @Test
    void testBubbleSortIntEmptyArray() {
        int[] nums = {};
        BubbleSort.bubbleSort(nums);
        assertArrayEquals(new int[]{}, nums);
    }

    @Test
    void testBubbleSortIntSingleElement() {
        int[] nums = {1};
        BubbleSort.bubbleSort(nums);
        assertArrayEquals(new int[]{1}, nums);
    }

    @Test
    void testBubbleSortIntWithDuplicates() {
        int[] nums = {4, 2, 4, 1, 2};
        BubbleSort.bubbleSort(nums);
        assertArrayEquals(new int[]{1, 2, 2, 4, 4}, nums);
    }

    // Test cases for bubbleSort with generic arrays
    @Test
    void testBubbleSortGenericNormalCase() {
        Integer[] nums = {4, 2, 7, 1, 3};
        BubbleSort.bubbleSort(nums);
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 7}, nums);
    }

    // Add more tests for generic types similar to the int array tests above
}
