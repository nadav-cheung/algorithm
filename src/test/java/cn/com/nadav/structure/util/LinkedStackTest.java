package cn.com.nadav.structure.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * email nadav.cheung@gmail.com
 * date 2024-02-08 11:23:21
 * description
 *
 * @author Nadav Cheung
 * @since 1.0.x
 */
class LinkedStackTest {

    @Test
    void testPush() {
        LinkedStack<Integer> stack = new LinkedStack<>();
        stack.push(1);
        assertEquals(1, stack.size(), "Stack size should be 1 after pushing an element.");
    }

    @Test
    void testPop() {
        LinkedStack<String> stack = new LinkedStack<>();
        stack.push("first");
        stack.push("second");
        assertEquals("second", stack.pop(), "Pop should return the last element pushed.");
        assertEquals(1, stack.size(), "Stack size should be 1 after popping one element.");
    }

    @Test
    void testPeek() {
        LinkedStack<Integer> stack = new LinkedStack<>();
        stack.push(10);
        stack.push(20);
        assertEquals(Integer.valueOf(20), stack.peek(), "Peek should return the top element without removing it.");
        assertEquals(2, stack.size(), "Stack size should remain unchanged after peeking.");
    }

    @Test
    void testEmpty() {
        LinkedStack<Integer> stack = new LinkedStack<>();
        assertTrue(stack.empty(), "New stack should be empty.");
        stack.push(10);
        assertFalse(stack.empty(), "Stack should not be empty after push operation.");
    }

    @Test
    void testPopEmptyStack() {
        LinkedStack<Integer> stack = new LinkedStack<>();
        assertNull(stack.pop(), "Pop on an empty stack should return null.");
    }

    @Test
    void testRecursiveAdd() {
        LinkedStack<Integer> stack = new LinkedStack<>();
        stack.push(1); // Assume push uses the add method internally
        stack.push(2);
        assertEquals(2, stack.size(), "Stack size should be 2 after pushing two elements.");
        assertEquals(Integer.valueOf(2), stack.peek(), "Peek should return the last element pushed.");
    }

    // Additional tests can be added to cover more scenarios and edge cases...
}
