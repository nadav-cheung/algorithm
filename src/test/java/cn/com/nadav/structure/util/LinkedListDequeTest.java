package cn.com.nadav.structure.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * email nadav.cheung@gmail.com
 * date 2024-02-07 20:26:00
 * description
 *
 * @author Nadav Cheung
 * @since 1.0.x
 */
class LinkedListDequeTest {

    private LinkedListDeque<Integer> deque;

    @BeforeEach
    void setUp() {
        deque = new LinkedListDeque<>();
    }

    @Test
    void testNewDequeIsEmpty() {
        assertTrue(deque.empty(), "New deque should be empty.");
    }

    @Test
    void testOfferAndPoll() {
        deque.offer(1);
        assertFalse(deque.empty(), "Deque should not be empty after offer.");
        assertEquals(1, deque.poll(), "Poll should return the first element offered.");
        assertTrue(deque.empty(), "Deque should be empty after poll.");
    }

    @Test
    void testSize() {
        deque.offer(1);
        deque.offer(2);
        assertEquals(2, deque.size(), "Size should reflect the number of elements in the deque.");
    }

    @Test
    void testPeek() {
        assertNull(deque.peek(), "Peek should return null for an empty deque.");
        deque.offer(1);
        assertEquals(1, deque.peek(), "Peek should return the first element.");
    }

    @Test
    void testOfferFirst() {
        deque.offerFirst(1);
        assertEquals(1, deque.peek(), "OfferFirst should add elements to the front of the deque.");
        deque.offerFirst(2);
        assertEquals(2, deque.peek(), "OfferFirst should move previous elements towards the tail.");
    }

    @Test
    void testPollLast() {
        assertNull(deque.pollLast(), "PollLast should return null for an empty deque.");
        deque.offer(1);
        deque.offer(2);
        assertEquals(2, deque.pollLast(), "PollLast should return and remove the last element.");
        assertEquals(1, deque.size(), "Size should decrease after pollLast.");
    }

    @Test
    void testEmptyAfterPoll() {
        deque.offer(1);
        deque.poll();
        assertTrue(deque.empty(), "Deque should be empty after removing the only element.");
    }
}
