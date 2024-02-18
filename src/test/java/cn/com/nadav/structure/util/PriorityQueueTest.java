package cn.com.nadav.structure.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PriorityQueueTest {

    private PriorityQueue<Integer> queue;

    @BeforeEach
    void setUp() {
        queue = new PriorityQueue<>();
    }

    @Test
    void shouldReturnTrueWhenOfferingElementToEmptyQueue() {
        assertTrue(queue.offer(1));
    }

    @Test
    void shouldReturnFalseWhenQueueIsEmpty() {
        assertTrue(queue.empty());
    }

    @Test
    void shouldReturnSizeOfQueue() {
        queue.offer(1);
        queue.offer(2);
        assertEquals(2, queue.size());
    }

    @Test
    void shouldReturnElementWhenPeekingIntoQueue() {
        queue.offer(1);
        assertEquals(1, queue.peek());
    }

    @Test
    void shouldReturnNullWhenPeekingIntoEmptyQueue() {
        assertNull(queue.peek());
    }

    @Test
    void shouldReturnElementWhenPollingFromQueue() {
        queue.offer(1);
        assertEquals(1, queue.poll());
    }

    @Test
    void shouldReturnNullWhenPollingFromEmptyQueue() {
        assertNull(queue.poll());
    }

    @Test
    void shouldThrowExceptionWhenOfferingNull() {
        assertThrows(NullPointerException.class, () -> queue.offer(null));
    }

    @Test
    void shouldThrowExceptionWhenCreatingQueueWithNegativeCapacity() {
        assertThrows(IllegalArgumentException.class, () -> new PriorityQueue<Integer>(-1));
    }

    @Test
    void shouldThrowExceptionWhenCreatingQueueWithZeroCapacity() {
        assertThrows(IllegalArgumentException.class, () -> new PriorityQueue<Integer>(0));
    }

    @Test
    void shouldThrowExceptionWhenCreatingQueueWithNullArray() {
        assertThrows(NullPointerException.class, () -> new PriorityQueue<Integer>(null));
    }

}