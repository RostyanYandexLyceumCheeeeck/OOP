package heapsort;

/**
 * Class of priority queue(max-heap).
 */
public class PriorityQueue {
    int[] heap;

    /**
     * @param arr to heap.
     */
    public PriorityQueue(int[] arr) {
        this.heap = arr;
        heap_sort();
    }

    /**
     * swap two values in the heap.
     * @param i -- index first value.
     * @param j -- index second value.
     */
    public void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    /**
     * sifting from top to bottom.
     * @param start -- index root heap.
     * @param end -- index end's heap.
     */
    public void sort_from_top(int start, int end) {
        int i = start;
        while (i < end) {
            int t = i;
            int l = 2 * i + 1;
            int r = 2 * i + 2;

            if (l < end && heap[l] > heap[t]) {
                t = l;
            }
            if (r < end && heap[r] > heap[t]) {
                t = r;
            }
            if (t != i) {
                swap(i, t);
                i = t;
            } else {
                break;
            }
        }
    }

    /**
     * convert array {@code heap} to max-heap.
     */
    public void heap_sort() {
        for (int i = heap.length / 2 - 1; i >= 0; i--) {
            sort_from_top(i, heap.length);
        }
    }

    /**
     * max-heap to sorted array.
     */
    public void normal_sort() {
        for (int i = heap.length - 1; i >= 0; i--) {
            swap(i, 0);
            sort_from_top(0, i);
        }
    }

    /**
     * @return heap.
     */
    public int[] getHeap() {
        return heap;
    }
}
