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

        private BinarySearchTree<Integer, String> tree;

        @BeforeEach
        void setUp() {
            tree = new BinarySearchTree<>();
        }

        @Test
        void shouldReturnTrueWhenTreeIsEmpty() {
            assertTrue(tree.empty());
        }

        @Test
        void shouldReturnSizeOfTree() {
            tree.add(1, "a");
            tree.add(2, "b");
            assertEquals(2, tree.getSize());
        }

        @Test
        void shouldReturnFalseWhenTreeIsNotEmpty() {
            tree.add(1, "a");
            assertFalse(tree.empty());
        }

        @Test
        void shouldReturnElementWhenPeekingIntoTree() {
            tree.add(1, "a");
            assertEquals("a", tree.get(1));
        }

        @Test
        void shouldReturnNullWhenPeekingIntoEmptyTree() {
            assertNull(tree.get(1));
        }

        @Test
        void shouldReturnElementWhenPollingFromTree() {
            tree.add(1, "a");
            assertEquals("a", tree.remove(1));
        }

        @Test
        void shouldReturnNullWhenPollingFromEmptyTree() {
            assertNull(tree.remove(1));
        }

        @Test
        void shouldThrowExceptionWhenOfferingNull() {
            assertThrows(NullPointerException.class, () -> tree.add(null, null));
        }

}
