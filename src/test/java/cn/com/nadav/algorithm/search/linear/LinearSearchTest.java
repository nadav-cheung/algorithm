package cn.com.nadav.algorithm.search.linear;

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
public class LinearSearchTest {

    // Tests for int array search
    @Test
    void testSearchIntFound() {
        int[] nums = {1, 3, 5, 7, 9};
        int target = 7;
        assertEquals(3, LinearSearch.search(nums, target));
    }

    @Test
    void testSearchIntNotFound() {
        int[] nums = {2, 4, 6, 8, 10};
        int target = 5;
        assertEquals(-1, LinearSearch.search(nums, target));
    }

    @Test
    void testSearchIntEmptyArray() {
        int[] nums = {};
        int target = 1;
        assertEquals(-1, LinearSearch.search(nums, target));
    }

    @Test
    void testSearchIntFirstElement() {
        int[] nums = {1, 3, 5, 7, 9};
        int target = 1;
        assertEquals(0, LinearSearch.search(nums, target));
    }

    @Test
    void testSearchIntLastElement() {
        int[] nums = {1, 3, 5, 7, 9};
        int target = 9;
        assertEquals(4, LinearSearch.search(nums, target));
    }

    // Tests for generic type array search
    @Test
    void testSearchGenericFound() {
        Integer[] nums = {1, 3, 5, 7, 9};
        int target = 7;
        assertEquals(3, LinearSearch.search(nums, target));
    }

    @Test
    void testSearchGenericNotFound() {
        Integer[] nums = {2, 4, 6, 8, 10};
        int target = 5;
        assertEquals(-1, LinearSearch.search(nums, target));
    }

    @Test
    void testSearchGenericFirstElement() {
        String[] strs = {"apple", "banana", "cherry"};
        String target = "apple";
        assertEquals(0, LinearSearch.search(strs, target));
    }

    @Test
    void testSearchGenericLastElement() {
        String[] strs = {"apple", "banana", "cherry"};
        String target = "cherry";
        assertEquals(2, LinearSearch.search(strs, target));
    }
}
