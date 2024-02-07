package cn.com.nadav.algorithm.search.binary;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * email nadav.cheung@gmail.com
 * date 2024-02-07 23:04:31
 * description
 *
 * @author Nadav Cheung
 * @since 1.0.x
 */
class BinarySearchTest {

    @Test
    void testRecursionSearchMiddleElement() {
        int[] array = {1, 3, 5, 7, 9};
        int target = 5;
        assertEquals(2, BinarySearch.recursionSearch(array, target));
    }

    @Test
    void testRecursionSearchFirstElement() {
        int[] array = {1, 3, 5, 7, 9};
        int target = 1;
        assertEquals(0, BinarySearch.recursionSearch(array, target));
    }

    @Test
    void testRecursionSearchLastElement() {
        int[] array = {1, 3, 5, 7, 9};
        int target = 9;
        assertEquals(4, BinarySearch.recursionSearch(array, target));
    }

    @Test
    void testRecursionSearchElementNotFound() {
        int[] array = {1, 3, 5, 7, 9};
        int target = 4;
        assertEquals(-1, BinarySearch.recursionSearch(array, target));
    }

    @Test
    void testRecursionSearchEmptyArray() {
        int[] array = {};
        int target = 1;
        assertEquals(-1, BinarySearch.recursionSearch(array, target));
    }

    @Test
    void testRecursionSearchNullArray() {
        int[] array = null;
        int target = 1;
        // Assuming recursionSearch handles null gracefully. Otherwise, expect an exception.
        assertEquals(-1, BinarySearch.recursionSearch(array, target));
    }

}