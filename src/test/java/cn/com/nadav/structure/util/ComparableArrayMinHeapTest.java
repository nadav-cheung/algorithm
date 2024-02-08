package cn.com.nadav.structure.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * email nadav.cheung@gmail.com
 * date 2024-02-08 10:39:24
 * description
 *
 * @author Nadav Cheung
 * @since 1.0.x
 */
class ComparableArrayMinHeapTest {

    @Test
    void testDefaultConstructor() {
        ComparableArrayMinHeap<Integer> heap = new ComparableArrayMinHeap<>();
        assertEquals(0, heap.size(), "Heap size should be 0 after initialization with default constructor.");
    }

    @Test
    void testCapacityConstructorValid() {
        ComparableArrayMinHeap<Integer> heap = new ComparableArrayMinHeap<>(10);
        assertEquals(0, heap.size(), "Heap size should be 0 after initialization with valid capacity.");
    }

    @Test
    void testCapacityConstructorInvalid() {
        assertThrows(IllegalArgumentException.class, () -> new ComparableArrayMinHeap<Integer>(-1),
                "Expected IllegalArgumentException for negative capacity.");
    }

    @Test
    void testArrayConstructor() {
        Integer[] data = {3, 1, 2};
        ComparableArrayMinHeap<Integer> heap = new ComparableArrayMinHeap<>(data);
        assertEquals(1, heap.peek(), "Heap should arrange elements according to min heap property.");
    }

    @Test
    void testArrayConstructorEmpty() {
        Integer[] data = {};
        ComparableArrayMinHeap<Integer> heap = new ComparableArrayMinHeap<>(data);
        assertEquals(0, heap.size(), "Heap size should be 0 when initialized with an empty array.");
    }

    @Test
    void testArrayConstructorNull() {
        assertThrows(NullPointerException.class, () -> new ComparableArrayMinHeap<Integer>(null),
                "Expected NullPointerException for null array.");
    }

    @Test
    void testPeekOnEmptyHeap() {
        ComparableArrayMinHeap<Integer> heap = new ComparableArrayMinHeap<>();
        assertNull(heap.peek(), "Peek should return null for an empty heap.");
    }

    @Test
    void testPollOnEmptyHeap() {
        ComparableArrayMinHeap<Integer> heap = new ComparableArrayMinHeap<>();
        assertNull(heap.poll(), "Poll should return null for an empty heap.");
    }

    @Test
    void testAddNull() {
        ComparableArrayMinHeap<Integer> heap = new ComparableArrayMinHeap<>();
        assertThrows(NullPointerException.class, () -> heap.add(null),
                "Expected NullPointerException when adding null.");
    }

    @Test
    void testAddAndPoll() {
        ComparableArrayMinHeap<Integer> heap = new ComparableArrayMinHeap<>();
        heap.add(5);
        heap.add(1);
        assertEquals(1, heap.poll(), "Poll should return the smallest element.");
        assertEquals(1, heap.size(), "Heap size should decrease after poll.");
    }

    @Test
    void testSizeAfterOperations() {
        ComparableArrayMinHeap<Integer> heap = new ComparableArrayMinHeap<>();
        heap.add(3);
        heap.add(1);
        heap.poll();
        assertEquals(1, heap.size(), "Size should reflect number of elements after add and poll operations.");
    }

// Additional edge cases or specific scenarios based on your implementation...
}
