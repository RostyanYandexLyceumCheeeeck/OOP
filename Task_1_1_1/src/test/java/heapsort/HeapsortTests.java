package heapsort;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Testing Heapsort.
 */
public class HeapsortTests {
    @Test
    public void test_1() {
        int[] arr = {};
        int[] expected = {};
        PriorityQueue queue = new PriorityQueue(arr);
        queue.normal_sort();
        Assertions.assertArrayEquals(expected, arr);
    }

    @Test
    public void test_2() {
        int[] arr = {1};
        int[] expected = {1};
        PriorityQueue queue = new PriorityQueue(arr);
        queue.normal_sort();
        Assertions.assertArrayEquals(expected, arr);
    }

    @Test
    public void test_3() {
        int[] arr = {2, 1};
        int[] expected = {1, 2};
        PriorityQueue queue = new PriorityQueue(arr);
        queue.normal_sort();
        Assertions.assertArrayEquals(expected, arr);
    }

    @Test
    public void test_4() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        PriorityQueue queue = new PriorityQueue(arr);
        queue.normal_sort();
        Assertions.assertArrayEquals(expected, arr);
    }

    @Test
    public void test_5() {
        int[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        PriorityQueue queue = new PriorityQueue(arr);
        queue.normal_sort();
        Assertions.assertArrayEquals(expected, arr);
    }

    @Test
    public void test_6() {
        int[] arr = {9, 9, 9, 8, 7, 6, 6, 6, 6, 5, 5, 4, 3, 2, 1};
        int[] expected = {1, 2, 3, 4, 5, 5, 6, 6, 6, 6, 7, 8, 9, 9, 9};
        PriorityQueue queue = new PriorityQueue(arr);
        queue.normal_sort();
        Assertions.assertArrayEquals(expected, arr);
    }

    @Test
    public void test_7() {
        int[] arr = {1, 4, 1, 3, 2, 1, 8, 1, 4, 9, 6, 6, 5, 9, 9, 8, 7, 6, 6, 5, 4, 1};
        int[] expected = {1, 1, 1, 1, 1, 2, 3, 4, 4, 4, 5, 5, 6, 6, 6, 6, 7, 8, 8, 9, 9, 9};
        PriorityQueue queue = new PriorityQueue(arr);
        queue.normal_sort();
        Assertions.assertArrayEquals(expected, arr);
    }
}
