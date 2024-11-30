package heapsort;

import java.util.Arrays;

/**
 * Class for entry point to the program.
 */
public class Main {
    /**
     * @param args arguments of command line.
     */
    public static void main(String[] args) {
        int[] arr = {9, 3, 4, -11, 0, 0, 0, 0, 2};
        PriorityQueue queue = new PriorityQueue(arr);
        queue.normal_sort();
        System.out.println(Arrays.toString(arr));
    }
}