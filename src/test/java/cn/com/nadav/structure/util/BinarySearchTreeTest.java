package cn.com.nadav.structure.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * email nadav.cheung@gmail.com
 * date 2024-02-08 10:22:38
 * description
 *
 * @author Nadav Cheung
 * @since 1.0.x
 */
public class BinarySearchTreeTest {

    private BinarySearchTree<Integer, String> bst;

    @BeforeEach
    void setUp() {
        bst = new BinarySearchTree<>();
    }

    @Test
    void testIsEmptyOnNewTree() {
        assertTrue(bst.isEmpty(), "A new tree should be empty");
    }

    @Test
    void testGetSizeOnNewTree() {
        assertEquals(0, bst.getSize(), "A new tree should have size 0");
    }

    @Test
    void testAddAndContains() {
        bst.add(1, "One");
        assertFalse(bst.isEmpty(), "Tree should not be empty after adding an element");
        assertEquals(1, bst.getSize(), "Tree size should be 1 after adding an element");
        assertTrue(bst.contains(1), "Tree should contain the key that was added");
    }

    @Test
    void testGetForExistingAndNonExistingKey() {
        bst.add(2, "Two");
        assertEquals("Two", bst.get(2), "Get should return the value for an existing key");
        assertNull(bst.get(3), "Get should return null for a non-existing key");
    }

    @Test
    void testSetForExistingKey() {
        bst.add(3, "Three");
        bst.set(3, "New Three");
        assertEquals("New Three", bst.get(3), "Set should update the value for an existing key");
    }

    @Test
    void testSetForNonExistingKey() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> bst.set(4, "Four"),
                "Setting a value for a non-existing key should throw IllegalArgumentException");
        assertNotNull(exception, "An exception should be thrown when setting a value for a non-existing key");
    }

    @Test
    void testRemoveExistingKey() {
        bst.add(5, "Five");
        assertEquals("Five", bst.remove(5), "Remove should return the value of the removed key");
        assertFalse(bst.contains(5), "Tree should not contain a key that was removed");
        assertEquals(0, bst.getSize(), "Tree size should decrease after removing an element");
    }

    @Test
    void testRemoveNonExistingKey() {
        assertNull(bst.remove(6), "Remove should return null when called with a non-existing key");
    }
}
