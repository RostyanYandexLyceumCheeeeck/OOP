package heapsort;

/**
 * Class of priority queue(max-heap).
 */
public class PriorityQueue {
    int[] heap;
    int capacity;
    int size;

    /**
     * initialize max-heap.
     *
     * @param arr to heap.
     */
    public PriorityQueue(int[] arr) {
        // TODO static method
        this.heap = arr;
        this.size = arr.length;
        this.capacity = arr.length;
        heap_sort();
    }

    /**
     * swap two values in the heap.
     *
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
     *
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
     * sifting from bottom to top.
     *
     * @param end -- index end's heap.
     */
    public void sort_from_bottom(int end) {
        int i = end;
        int j = i / 2;
        while (i > 0 && heap[j] > heap[i]) {
            swap(i, j);
            i = j;
            j = i / 2;
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * get and delete max value in heap.
     *
     * @return max value.
     */
    public int pop_top() {
        if (isEmpty()) {
            return -1;
        }

        int result = heap[0];
        swap(0, size - 1);
        size--;
        sort_from_top(0, size - 1);
        return result;
    }

    /**
     * add value to heap.
     *
     * @param value -- add value.
     */
    public void add(int value) {
        if (size == capacity) {
            capacity *= 2;
            int[] temp = new int[capacity];
            System.arraycopy(heap, 0, temp, 0, heap.length);
            heap = temp;
        }
        heap[size++] = value;
        sort_from_bottom(size - 1);
    }

    /**
     * convert array {@code heap} to max-heap.
     */
    public void heap_sort() {
        for (int i = size / 2 - 1; i >= 0; i--) {
            sort_from_top(i, size);
        }
    }

    /**
     * max-heap in-place sorted array.
     */
    public void normal_sort() {
        for (int i = size - 1; i >= 0; i--) {
            swap(i, 0);
            sort_from_top(0, i);
        }
    }

    /**
     * copy max-heap sorted array.
     *
     * @return copy sorted heap
     */
    public int[] normal_sort_copy() {
        int[] temp = heap;
        normal_sort();
        int[] result = new int[size];
        System.arraycopy(heap, 0, result, 0, size);
        heap = temp;
        return result;
    }

    /**
     * get heap.
     *
     * @return heap.
     */
    public int[] getHeap() {
        return heap;
    }
}
