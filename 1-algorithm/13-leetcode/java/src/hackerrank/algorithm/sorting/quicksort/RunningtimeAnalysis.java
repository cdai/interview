package hackerrank.algorithm.sorting.quicksort;

import java.util.Scanner;

/**
 * Compare the running time of insertion sort and quicksort.
 */
public class RunningtimeAnalysis {

    public static void main(String[] args) {
        RunningtimeAnalysis sol = new RunningtimeAnalysis();
        try (Scanner in = new Scanner(System.in)) {
            int[] A = new int[in.nextInt()];
            int[] B = new int[A.length];
            for (int i = 0; i < A.length; i++)
                B[i] = A[i] = in.nextInt();

            sol.insertionSort(A);
            sol.quicksort(B, 0, B.length - 1);

            System.out.println(sol.shifts - sol.swaps);
        }
    }

    private int shifts = 0;
    private int swaps = 0;

    public void insertionSort(int[] A) {
        for (int i = 1; i < A.length; i++) {
            int key = A[i];
            int j = i - 1;
            for (; j >= 0 && A[j] > key; j--, shifts++)
                A[j + 1] = A[j];
            A[j + 1] = key;
        }
    }

    public void quicksort(int[] A, int low, int high) {
        if (low >= high) return;
        int mid = partition(A, low, high);
        quicksort(A, low, mid - 1);
        quicksort(A, mid + 1, high);
    }

    private int partition(int[] A, int low, int high) {
        int j = low;
        for (int i = low, pivot = A[high]; i < high; i++)
            if (A[i] <= pivot)
                swap(A, i, j++);
        swap(A, j, high);
        return j;
    }

    private void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
        swaps++;
    }

}
