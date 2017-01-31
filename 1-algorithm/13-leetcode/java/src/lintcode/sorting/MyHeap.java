package lintcode.sorting;

import org.junit.jupiter.api.Assertions;

/**
 */
public class MyHeap {

    public static void main(String[] args) {
        MyHeap h = new MyHeap(10);
        h.offer(4);
        h.offer(4);
        Assertions.assertEquals(4, h.peek());
        h.offer(3);
        Assertions.assertEquals(3, h.peek());
        h.offer(1);
        h.offer(2);
        Assertions.assertEquals(1, h.peek());
        h.offer(9);
        Assertions.assertEquals(1, h.peek());
        Assertions.assertArrayEquals(new int[]{1, 2, 3, 4, 4, 9}, h.heapsort());
    }

    private int size = 0;
    private int[] heap;

    public MyHeap(int capacity) {
        this.heap = new int[capacity];
    }

    public int peek() {
        return heap[0];
    }

    public int[] heapsort() {
        int[] ret = new int[size];
        for (int i = size; i > 0; i--) { // size changed by poll()
            ret[ret.length - i] = poll();
        }
        return ret;
    }

    public int poll() {
        int min = heap[0];
        heap[0] = heap[--size];
        heapify(0);             // Float down
        return min;
    }

    public void offer(int x) {  // Float up
        heap[size++] = x;
        int i = size - 1;
        while (i > 0) {         // Compare with parent until no parent
            int p = (i - 1) / 2;// Start with zero, a little mess for index calculation
            if (heap[p] < heap[i]) break;
            swap(p, i);
            i = p;
        }
    }

    private void heapify(int i) {
        int l = 2 * i + 1, r = 2 * i + 2;
        int min = i;
        if (l < size && heap[l] < heap[min]) min = l;
        if (r < size && heap[r] < heap[min]) min = r;
        if (min != i) {
            swap(i, min);
            heapify(min);
        }
    }

    private void swap(int i, int j) {
        int tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }

    // Lintcode-130 Heapify
    public void heapify(int[] A) {
        for (int i = A.length / 2; i >= 0; i--) { // Start from node that has children
            heapify(A, i);
        }
    }

    private void heapify(int[] A, int i) {
        int l = 2 * i + 1, r = 2 * i + 2, n = A.length;
        int min = i;
        if (l < n && A[l] < A[min]) min = l;
        if (r < n && A[r] < A[min]) min = r;
        if (min != i) {
            int tmp = A[i];
            A[i] = A[min];
            A[min] = tmp;
            heapify(A, min);
        }
    }

}
