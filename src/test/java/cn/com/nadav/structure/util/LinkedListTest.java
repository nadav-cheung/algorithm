package cn.com.nadav.structure.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * email nadav.cheung@gmail.com
 * date 2024-02-08 11:18:44
 * description
 *
 * @author Nadav Cheung
 * @since 1.0.x
 */
public class LinkedListTest {

    @Test
    public void testEmptyList() {
        LinkedList<Integer> list = new LinkedList<>();
        assertTrue(list.empty(), "List should be empty upon initialization.");
    }

    @Test
    public void testAddToEnd() {
        LinkedList<String> list = new LinkedList<>();
        list.add("Hello");
        assertFalse(list.empty(), "List should not be empty after adding an element.");
        assertEquals(1, list.size(), "List size should be 1 after adding one element.");
    }

    @Test
    public void testAddAtIndex() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(0, 1);
        list.add(1, 3);
        list.add(1, 2); // List should be [1, 2, 3]
        assertEquals(3, list.size(), "List size should be 3.");
        assertEquals(Integer.valueOf(2), list.get(1), "Element at index 1 should be 2.");
    }

    @Test
    public void testRemoveByIndex() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3); // List is [1, 2, 3]
        Integer removed = list.remove(1); // Remove element at index 1
        assertEquals(Integer.valueOf(2), removed, "Removed element should be 2.");
        assertEquals(2, list.size(), "List size should be 2 after removal.");
    }

    @Test
    public void testRemoveObject() {
        LinkedList<String> list = new LinkedList<>();
        list.add("A");
        list.add("B");
        list.add("C"); // List is ["A", "B", "C"]
        boolean removed = list.remove("B");
        assertTrue(removed, "Element 'B' should be successfully removed.");
        assertEquals(2, list.size(), "List size should be 2 after removal.");
    }

    @Test
    public void testGetInvalidIndex() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(2), "Accessing with an invalid index should throw IndexOutOfBoundsException.");
    }

    // Continue with more tests covering all methods and scenarios...
}
