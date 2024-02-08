package cn.com.nadav.structure.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * email nadav.cheung@gmail.com
 * date 2024-02-08 10:11:20
 * description
 *
 * @author Nadav Cheung
 * @since 1.0.x
 */
public class ArrayDequeTest {

    private ArrayDeque<Integer> deque;

    @BeforeEach
    void setUp() {
        deque = new ArrayDeque<>();
    }

    @Test
    void testEmptyConstructor() {
        assertEquals(0, deque.size(), "Deque should be empty.");
    }

    @Test
    void testCapacityConstructor() {
        int initialCapacity = 10;
        ArrayDeque<Integer> customDeque = new ArrayDeque<>(initialCapacity);
        // Assuming there's a way to check the internal capacity, not just the size
        assertTrue(customDeque.capacity() >= initialCapacity, "Deque capacity should be at least " + initialCapacity);
    }

    @Test
    void testOfferFirstWithNonNullElement() {
        assertTrue(deque.offerFirst(1), "offerFirst should return true.");
        assertEquals(1, deque.size(), "Deque should contain one element.");
    }

    @Test
    void testOfferFirstWithNullElement() {
        assertThrows(NullPointerException.class, () -> deque.offerFirst(null), "Offering null should throw NullPointerException.");
    }

    @Test
    void testPollLastFromEmptyDeque() {
        assertNull(deque.pollLast(), "Polling from an empty deque should return null.");
    }

    @Test
    void testPollLastReturnsCorrectElement() {
        Integer firstElement = 1;
        Integer lastElement = 2;
        deque.offerFirst(firstElement);
        deque.offerFirst(lastElement);
        assertEquals(firstElement, deque.pollLast(), "Polling last should return the first element offered.");
        assertEquals(1, deque.size(), "Deque should have one element left.");
    }
}
