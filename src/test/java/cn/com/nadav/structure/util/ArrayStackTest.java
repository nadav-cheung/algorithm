package cn.com.nadav.structure.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * email nadav.cheung@gmail.com
 * date 2024-02-08 10:17:09
 * description
 *
 * @author Nadav Cheung
 * @since 1.0.x
 */
class ArrayStackTest {
    private ArrayStack<Integer> stack;

    @BeforeEach
    void setUp() {
        stack = new ArrayStack<>();
    }


    @Test
    @DisplayName("Should create an empty stack with default capacity")
    void testDefaultConstructor() {
        assertEquals(0, stack.size(), "Stack should be empty.");
    }

    @Test
    @DisplayName("Should create an empty stack with specified initial capacity")
    void testCapacityConstructor() {
        int initialCapacity = 20;
        ArrayStack<Integer> customStack = new ArrayStack<>(initialCapacity);
        assertEquals(0, customStack.size(), "Stack should be empty.");
        // This assumes there's a way to check the actual array capacity, which might not be exposed by the class
    }

    @Test
    @DisplayName("Should push an element onto the stack")
    void testPush() {
        Integer element = 10;
        stack.push(element);
        assertEquals(1, stack.size(), "Stack should contain one element.");
    }

    @Test
    @DisplayName("Should grow when pushing beyond capacity")
    void testPushBeyondCapacity() {
        for (int i = 0; i < 17; i++) { // Assuming DEFAULT_CAPACITY is 16
            stack.push(i);
        }
        assertEquals(17, stack.size(), "Stack should grow to accommodate more elements.");
    }


    @Test
    @DisplayName("Should return null when popping from an empty stack")
    void testPopEmptyStack() {
        assertNull(stack.pop(), "Popping from an empty stack should return null.");
    }

    @Test
    @DisplayName("Should pop the last element pushed onto the stack")
    void testPop() {
        Integer element = 10;
        stack.push(element);
        assertEquals(element, stack.pop(), "Pop should return the last element pushed.");
        assertEquals(0, stack.size(), "Stack should be empty after pop.");
    }


    @Test
    @DisplayName("Should return null when peeking into an empty stack")
    void testPeekEmptyStack() {
        assertNull(stack.peek(), "Peeking into an empty stack should return null.");
    }

    @Test
    @DisplayName("Should peek the last element pushed onto the stack without removing it")
    void testPeek() {
        Integer element = 10;
        stack.push(element);
        assertEquals(element, stack.peek(), "Peek should return the last element pushed.");
        assertEquals(1, stack.size(), "Stack size should remain the same after peek.");
    }


    @Test
    @DisplayName("Should check if the stack is empty")
    void testEmpty() {
        assertTrue(stack.empty(), "Newly created stack should be empty.");
        stack.push(10);
        assertFalse(stack.empty(), "Stack with elements should not be empty.");
    }

    @Test
    @DisplayName("Should return the correct size")
    void testSize() {
        assertEquals(0, stack.size(), "Newly created stack should have size 0.");
        stack.push(10);
        assertEquals(1, stack.size(), "Stack should have size 1 after one push.");
    }


}