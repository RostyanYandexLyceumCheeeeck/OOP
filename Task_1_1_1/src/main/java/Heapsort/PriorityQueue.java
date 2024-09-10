package Heapsort;

public class PriorityQueue {
    int[] heap;
    int size;
    
    public PriorityQueue(int[] arr) {
        this.heap = arr;
        this.size = arr.length;
        heap_sort();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

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
    public void sort_from_bottom(int end) {
        int i = end;
        int j = i / 2;
        while (i > 0 && heap[j] > heap[i]) {
            swap(i, j);
            i = j;
            j = i / 2;
        }
    }

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

    public void heap_sort() {
        for (int i = size / 2 - 1; i >= 0; i--) {
            sort_from_top(i, size);
        }
    }

    public void normal_sort() {
        for (int i = size - 1; i >= 0; i--) {
            swap(i, 0);
            sort_from_top(0, i);
        }
    }

    public int[] normal_sort_copy() {
        int[] temp = heap;
        normal_sort();
        int[] result = new int[size];
        System.arraycopy(heap, 0, result, 0, size);
        heap = temp;
        return result;
    }

    public int[] getHeap() {
        return heap;
    }
}
