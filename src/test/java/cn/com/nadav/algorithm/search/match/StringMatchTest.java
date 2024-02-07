package cn.com.nadav.algorithm.search.match;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * email nadav.cheung@gmail.com
 * date 2024-02-07 23:22:29
 * description
 *
 * @author Nadav Cheung
 * @since 1.0.x
 */
public class StringMatchTest {

    // Tests for bruteForce method
    @Test
    void testBruteForceMatchFound() {
        assertEquals(2, StringMatch.bruteForce("hello world", "llo"));
    }

    @Test
    void testBruteForceNoMatch() {
        assertEquals(-1, StringMatch.bruteForce("hello world", "abc"));
    }

    @Test
    void testBruteForcePatternAtStart() {
        assertEquals(0, StringMatch.bruteForce("hello world", "hel"));
    }

    @Test
    void testBruteForcePatternAtEnd() {
        assertEquals(6, StringMatch.bruteForce("hello world", "world"));
    }

    @Test
    void testBruteForceEmptySource() {
        assertEquals(-1, StringMatch.bruteForce("", "world"));
    }

    @Test
    void testBruteForceEmptyPattern() {
        assertEquals(0, StringMatch.bruteForce("hello world", ""));
    }

    @Test
    void testBruteForceEmptySourceAndPattern() {
        assertEquals(0, StringMatch.bruteForce("", ""));
    }

    @Test
    void testBruteForcePatternLongerThanSource() {
        assertEquals(-1, StringMatch.bruteForce("hello", "hello world"));
    }

    // Tests for search (KMP) method
    @Test
    void testSearchMatchFound() {
        assertEquals(2, StringMatch.search("hello world", "llo"));
    }

    @Test
    void testSearchNoMatch() {
        assertEquals(-1, StringMatch.search("hello world", "abc"));
    }

    @Test
    void testSearchPatternAtStart() {
        assertEquals(0, StringMatch.search("hello world", "hel"));
    }

    @Test
    void testSearchPatternAtEnd() {
        assertEquals(6, StringMatch.search("hello world", "world"));
    }

    @Test
    void testSearchEmptySource() {
        assertEquals(-1, StringMatch.search("", "world"));
    }

    @Test
    void testSearchEmptyPattern() {
        assertEquals(0, StringMatch.search("hello world", ""));
    }

    @Test
    void testSearchEmptySourceAndPattern() {
        assertEquals(0, StringMatch.search("", ""));
    }

    @Test
    void testSearchPatternLongerThanSource() {
        assertEquals(-1, StringMatch.search("hello", "hello world"));
    }
}
