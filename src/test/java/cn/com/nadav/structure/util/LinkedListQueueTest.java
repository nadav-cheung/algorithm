package cn.com.nadav.structure.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListQueueTest {

    @Test
    void testConstructor() {
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        assertTrue(queue.empty(), "Queue should be empty after initialization.");
    }

    @Test
    void testOfferAndSize() {
        LinkedListQueue<String> queue = new LinkedListQueue<>();
        queue.offer("first");
        assertEquals(1, queue.size(), "Queue size should be 1 after offering one element.");
        queue.offer("second");
        assertEquals(2, queue.size(), "Queue size should be 2 after offering another element.");
    }

    @Test
    void testPollEmptyQueue() {
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        assertNull(queue.poll(), "Polling from an empty queue should return null.");
    }

    @Test
    void testPollSingleElement() {
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        queue.offer(10);
        assertEquals(Integer.valueOf(10), queue.poll(), "Poll should return the first element.");
        assertTrue(queue.empty(), "Queue should be empty after polling the last element.");
    }

    @Test
    void testPeek() {
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        queue.offer(10);
        queue.offer(20);
        assertEquals(Integer.valueOf(10), queue.peek(), "Peek should return the first element without removing it.");
        assertEquals(2, queue.size(), "Queue size should remain unchanged after peeking.");
    }

    @Test
    void testEmpty() {
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        assertTrue(queue.empty(), "Queue should be empty initially.");
        queue.offer(10);
        assertFalse(queue.empty(), "Queue should not be empty after offering elements.");
    }

    // Add more tests as needed to cover all scenarios and edge cases...
}
