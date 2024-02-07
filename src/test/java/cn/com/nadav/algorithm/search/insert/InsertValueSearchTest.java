package cn.com.nadav.algorithm.search.insert;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * email nadav.cheung@gmail.com
 * date 2024-02-07 23:11:30
 * description
 *
 * @author Nadav Cheung
 * @since 1.0.x
 */
public class InsertValueSearchTest {

    @Test
    void testInterpolationSearchNormalCase() {
        int[] arr = {10, 20, 30, 40, 50};
        int target = 30;
        assertEquals(2, InsertValueSearch.interpolationSearch(arr, 0, arr.length - 1, target));
    }

    @Test
    void testInterpolationSearchEdgeCaseFirstElement() {
        int[] arr = {5, 10, 20, 30, 40};
        int target = 5;
        assertEquals(0, InsertValueSearch.interpolationSearch(arr, 0, arr.length - 1, target));
    }

    @Test
    void testInterpolationSearchEdgeCaseLastElement() {
        int[] arr = {10, 20, 30, 40, 50};
        int target = 50;
        assertEquals(4, InsertValueSearch.interpolationSearch(arr, 0, arr.length - 1, target));
    }

    @Test
    void testInterpolationSearchNotFound() {
        int[] arr = {10, 20, 30, 40, 50};
        int target = 25;
        assertEquals(-1, InsertValueSearch.interpolationSearch(arr, 0, arr.length - 1, target));
    }

    @Test
    void testInterpolationSearchEmptyArray() {
        int[] arr = {};
        int target = 10;
        assertEquals(-1, InsertValueSearch.interpolationSearch(arr, 0, arr.length - 1, target));
    }

    @Test
    void testInterpolationSearchSingleElementArrayTargetPresent() {
        int[] arr = {10};
        int target = 10;
        assertEquals(0, InsertValueSearch.interpolationSearch(arr, 0, arr.length - 1, target));
    }

    @Test
    void testInterpolationSearchSingleElementArrayTargetAbsent() {
        int[] arr = {10};
        int target = 5;
        assertEquals(-1, InsertValueSearch.interpolationSearch(arr, 0, arr.length - 1, target));
    }

    @Test
    void testInterpolationSearchTargetOutOfRange() {
        int[] arr = {10, 20, 30, 40, 50};
        int target = 60; // Target greater than the largest element
        assertEquals(-1, InsertValueSearch.interpolationSearch(arr, 0, arr.length - 1, target));

        target = 5; // Target less than the smallest element
        assertEquals(-1, InsertValueSearch.interpolationSearch(arr, 0, arr.length - 1, target));
    }
}
